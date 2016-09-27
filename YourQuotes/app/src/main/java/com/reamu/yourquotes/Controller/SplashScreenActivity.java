package com.reamu.yourquotes.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.reamu.yourquotes.Integrations_layer.DAO.ReadQuoteDAO;
import com.reamu.yourquotes.FrontEnd.MainActivity;
import com.reamu.yourquotes.R;
import com.reamu.yourquotes.models.QuotesModel;

import java.util.ArrayList;


/**
 * Created by Reena on 10-06-2016.
 */
public class SplashScreenActivity extends Activity {
    private DotProgressBar mProgressBar;
    private Context mContext;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_splash);
        initViews();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                new LoadQuotesAsync().execute();
            }
        }, SPLASH_TIME_OUT);


    }


    public void initViews() {
        mProgressBar = (DotProgressBar) findViewById(R.id.dot_progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public class LoadQuotesAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressBar.animate();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ReadQuoteDAO.getInstance().readJsonFromText(mContext, new ReadQuoteDAO.LoadQuotes() {
                @Override
                public void getQuotes(ArrayList<QuotesModel> arrQuotes) {
                    MyApplication.setmArrListQuotes(arrQuotes);
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent intentMain = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intentMain);
            finish();
        }

    }
}
