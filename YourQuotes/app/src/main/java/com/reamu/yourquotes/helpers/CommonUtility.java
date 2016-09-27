package com.reamu.yourquotes.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;


/**
 * Created by Reena on 28/12/15.
 */
public class CommonUtility {

    private static CommonUtility mUtility;
    private static ProgressDialog progress;
    //Context mcontext;

    // Singleton Class method to access application state instance
    public static CommonUtility sharedInstance() {

        if (mUtility == null)
            mUtility = new CommonUtility();

        return mUtility;
    }

    public Typeface getTypeFace(Context context, String stringFontName) {
        Typeface typefaceSemiBold = Typeface.createFromAsset(context.getAssets(), stringFontName);

        return typefaceSemiBold;

    }

    public void showProgress(String title, String message, Context context) {
        if (progress == null) {
            progress = new ProgressDialog(context);
        } else {
            if (progress.isShowing())
                progress.dismiss();
        }
        progress = new ProgressDialog(context);
        progress.setTitle(title);
        progress.setCancelable(false);
        progress.setMessage(message);
        progress.show();
    }

    public void dismissProgress() {
        // To dismiss the dialog
        if (progress.isShowing())
            progress.dismiss();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static String getLocationBeforeComma(String location) {
        String[] strNewLoc = location.split(",");

        return strNewLoc[0];
    }

    public static String currentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());
        Log.e("currentDate-->>", "strDate:" + strDate);
        return strDate;
    }


    public boolean checkDateValidity(Date date) {
        Calendar cal = new GregorianCalendar(date.getYear() + 1900, date.getMonth(), date.getDate(), date.getHours(), date.getMinutes());
        Log.e(getClass().getSimpleName(), "Date difference:" + System.currentTimeMillis() + ":" + (cal.getTimeInMillis() - System.currentTimeMillis()));


        if ((cal.getTimeInMillis() - System.currentTimeMillis()) < 0) {
            return false;
        } else {
            return true;
        }
    }

    public void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }

    public void showSnackbar(CoordinatorLayout layout, Context context, String msg) {
        Snackbar snackbar = Snackbar
                .make(layout, msg, Snackbar.LENGTH_LONG)
                .setAction("", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        // Changing message text color
//        snackbar.setActionTextColor(Color.RED);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }

    /**
     * Show Toast
     *
     * @param context
     * @param msg
     */
    public void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void shareOnSocial(Context context, String quote) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, quote);
        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
        context.startActivity(sendIntent);
    }


}



