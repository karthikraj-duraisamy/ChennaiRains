package net.karthikraj.apps.chennairains.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class AidNeededTable {

    //timeStamp, name, whatKind, area, contactNumber, originalSource, others, coloumnID;
    //The columns we'll include in the dictionary table
    public static final String COLUMN_ID = "_id";
    public static final String AIDNEED_TIMESTAMP = "aidneed_timestamp";
    public static final String AIDNEED_NAME = "aidneed_name";
    public static final String AIDNEED_WHATKIND = "aidneed_whatkind";
    public static final String AIDNEED_AREA = "aidneed_area";
    public static final String AIDNEED_CONT_NUMBER = "aidneed_cont_number";
    public static final String AIDNEED_ORIGINAL_SOURCE = "aidneed_orignal_source";
    public static final String AIDNEED_OTHERS = "aidneed_others";



    public static final String AIDNEED_TABLE = "aidneededtable";

    // Database creation SQL statement

    private static final String AIDNEED_TABLE_CREATE = "create table "
            + AIDNEED_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + AIDNEED_TIMESTAMP + " text null, "
            + AIDNEED_CONT_NUMBER + " text null,"
            + AIDNEED_WHATKIND + " text null,"
            + AIDNEED_AREA + " text null,"
            + AIDNEED_ORIGINAL_SOURCE + " text null,"
            + AIDNEED_NAME + " text null,"
            + AIDNEED_OTHERS + " text null,"
            + " text null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(AIDNEED_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + AIDNEED_TABLE);
        onCreate(database);
    }


}
