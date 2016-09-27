package com.reamu.yourquotes.Integrations_layer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.reamu.yourquotes.helpers.QuotesConstants;

/**
 * Created by scispl15 on 27-09-2016.
 */

public class QuotesDatabase extends SQLiteOpenHelper {


    public QuotesDatabase(Context context) {
        super(context, QuotesConstants.DATABASE_NAME, null, QuotesConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
