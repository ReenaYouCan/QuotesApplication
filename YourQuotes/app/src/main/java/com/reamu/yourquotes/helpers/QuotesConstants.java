package com.reamu.yourquotes.helpers;

/**
 * Created by scispl15 on 27-09-2016.
 */

public class QuotesConstants {


    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "quotesDatabase";

    // Contacts table name
    public static final String TABLE_USER_DETAILS = "userTable";


    // USER Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PH_NO = "phone_number";
    public static final String KEY_MY_QUOTES = "my_quotes";

    String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER_DETAILS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_PH_NO + " TEXT" + ")";

    // Quotes Table Columns names

    public static final String TABLE_QUOTES = "quotesTable";
    public static final String KEY_QUOTE_ID = "quote_id";
    public static final String KEY_QUOTE_TEXT = "quote_text";
    public static final String KEY_QUOTE_AUTHER = "quote_auther";
    public static final String KEY_QUOTE_TOPIC = "quote_topic";


    // Auther Table Columns names
    public static final String TABLE_AUTHER = "autherTable";
    public static final String KEY_AUTHER_ID = "auther_id";
    public static final String KEY_AUTHER_NAME = "auther_name";
    public static final String KEY_AUTHER_IMAGE_URL = "auther_img_url";
    public static final String KEY_AUTHER_BIRTHDATE = "auther_birthdate";

}
