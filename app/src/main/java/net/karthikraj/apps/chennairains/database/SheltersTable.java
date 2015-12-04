package net.karthikraj.apps.chennairains.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class SheltersTable {


    //timeStamp, area, acoomadationNumbers, socialProfiles, contactNumber, originalSource,
    // others, coloumnID;
    //The columns we'll include in the dictionary table
    public static final String COLUMN_ID = "_id";
    public static final String SHELT_TIMESTAMP = "shelt_timestamp";
    public static final String SHELT_AREA = "shelt_area";
    public static final String SHELT_ACCOM_NUMBERS = "shelt_accom_numbers";
    public static final String SHELT_SOCIAL_PROFILE = "shelt_social_profile";
    public static final String SHELT_CONT_NUMBER = "shelt_cont_number";
    public static final String SHELT_ORIG_SOURCE = "shelt_orig_source";
    public static final String SHELT_OTHERS = "shelt_others";



    public static final String SHELTERS_TABLE = "shelterstable";

    // Database creation SQL statement

    private static final String SHELTERS_TABLE_CREATE = "create table "
            + SHELTERS_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + SHELT_TIMESTAMP + " text null, "
            + SHELT_AREA + " text null,"
            + SHELT_ACCOM_NUMBERS + " text null,"
            + SHELT_SOCIAL_PROFILE + " text null,"
            + SHELT_CONT_NUMBER + " text null,"
            + SHELT_ORIG_SOURCE + " text null,"
            + SHELT_OTHERS + " text null,"
            + " text null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(SHELTERS_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + SHELTERS_TABLE);
        onCreate(database);
    }

}
