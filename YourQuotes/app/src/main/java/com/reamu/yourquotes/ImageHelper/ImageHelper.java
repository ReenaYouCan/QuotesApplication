package com.reamu.yourquotes.ImageHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by monica on 12/3/2015.
 */
public class ImageHelper {

    private static String TAG = "ImageHelper";
    //decide the max size as per the imageview size or size required.
    //private final int IMAGE_MAX_SIZE = 250;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static ImageHelper mImageHelper = null;

    public static ImageHelper getSharedInstance() {
        if (mImageHelper == null) {
            mImageHelper = new ImageHelper();
        }

        return mImageHelper;
    }

    public void beginCropIntent(Uri cropInputUri, Uri cropOutputUri, Activity activity, int requestCode) {

        Intent cropApps = new Intent("com.android.camera.action.CROP");
        cropApps.setType("image/*");
        List<ResolveInfo> list = activity.getPackageManager().queryIntentActivities(cropApps, 0);
        int size = list.size();
        if (size == 0) {
            Toast.makeText(activity, "Can not find image crop app", Toast.LENGTH_SHORT).show();
        } else {
            ResolveInfo res = list.get(0);
            Intent cropIntent = new Intent();
            cropIntent.setClassName(res.activityInfo.packageName, res.activityInfo.name);
            cropIntent.setDataAndType(cropInputUri, "image/*");
            //indicate output X and Y
            DisplayMetrics displaymetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            cropIntent.putExtra("outputX", 250); //width in pixels
            cropIntent.putExtra("outputY", 250);// height in pixels
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1); //aspect ration x:y
            cropIntent.putExtra("aspectY", 1);//aspect ration x:y
            cropIntent.putExtra("scale", true);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, cropOutputUri);
            activity.startActivityForResult(cropIntent, requestCode);
        }
    }


    /**
     * Create a file Uri for saving an image or video
     */
    public static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }


    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "4WheelConsulting");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.


        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("CloudACar", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        File mediaFile = null;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "TS_TEMP.jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    /**
     * Gets the real path of the selected/captured image
     *
     * @param contentURI
     * @return
     */
    public String getRealPathFromURI(Uri contentURI, Context context, int flag) {
        String imagePath;
        try {
            Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) {
                imagePath = contentURI.getPath();
            } else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                imagePath = cursor.getString(idx);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return imagePath;
    }

//    public void renameProfilePic(String userId) {
//
//        File dir = new File(SaConstants.getInstance().BASE_FOLDER_PATH);/*Environment.getExternalStorageDirectory()*/
//        if (dir.exists()) {
//            File from = new File(dir, "PR_IMG.png");
//            File to = new File(dir, "PR_IMG_" + userId + ".png");
//            if (from.exists())
//                from.renameTo(to);
//        }
//    }

    public Bitmap smallBitmap(Bitmap bm, String filePath) {
        Bitmap bitmap = bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        final int REQUIRED_SIZE = 100;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(filePath, options);

        return bitmap;
    }


    public Bitmap rotateImageWithSpecificAngle(Bitmap bitmap, String photoPath) {

        Bitmap returnedBitmap = null;
        try {

            ExifInterface ei = new ExifInterface(photoPath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            Log.e(getClass().getSimpleName(), "Orientation:" + orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    Log.e(getClass().getSimpleName(), "Ratate:90");
                    returnedBitmap = rotateImage(bitmap, 90);
//                    returnedBitmap.recycle();
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    returnedBitmap = rotateImage(bitmap, 180);
                    Log.e(getClass().getSimpleName(), "Ratate:180");
//                    returnedBitmap.recycle();
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    returnedBitmap = rotateImage(bitmap, 270);
                    Log.e(getClass().getSimpleName(), "Ratate:270");
//                    returnedBitmap.recycle();
                    break;

                // etc.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedBitmap;
    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        Bitmap bmp = source;
        if (source != null) {

            matrix.postRotate(angle);

            Log.e(getClass().getSimpleName(), "Bitmap Not null");
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        } else {
            Log.e(getClass().getSimpleName(), "Bitmap null");
        }
        return null;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri, Context context) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void setImage(final String imageUrl, final ImageView imageView, final Boolean isLogo, final Context context) {
        DisplayImageOptions defaultOptions;
        Log.e(TAG, " iamge uri -> " + imageUrl);

        defaultOptions = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(700))
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.loadImage(imageUrl, defaultOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                Log.e(TAG, imageUri + " iamge in loader uri ->\n " + imageUrl);

                Drawable drawable = new BitmapDrawable(context.getResources(), loadedImage);

                if (isLogo) {
                    imageView.setImageBitmap(loadedImage);

                } else {
                    imageView.setImageBitmap(null);
                    imageView.setBackground(drawable);
                }
            }
        });
    }


    public String convertImageToBase64(Bitmap bitmap) {


        String imgStr = "";
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream); //compress to which format you want.

            byte[] byte_arr = byteArrayBitmapStream.toByteArray();
            imgStr = Base64.encodeToString(byte_arr, Base64.DEFAULT);
            //Log.i(TAG, "imgStr: " + imgStr);
            return imgStr;
        }

        return imgStr;
    }
}
