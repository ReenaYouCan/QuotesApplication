package com.reamu.yourquotes.Controller;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.reamu.yourquotes.models.QuotesModel;

import java.util.ArrayList;

/**
 * Created by Reena on 08-06-2016.
 */
public class MyApplication extends MultiDexApplication {

    public static MyApplication instance;

    public static ArrayList<QuotesModel> mArrListQuotes = null;


    public static ArrayList<QuotesModel> getmArrListQuotes() {
        return mArrListQuotes;
    }

    public static void setmArrListQuotes(ArrayList<QuotesModel> mArrListQuotes) {
        MyApplication.mArrListQuotes = mArrListQuotes;
    }

    /*Instance*/
    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
