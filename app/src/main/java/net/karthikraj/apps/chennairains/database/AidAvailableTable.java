package net.karthikraj.apps.chennairains.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class AidAvailableTable {


    //timeStamp, contactNumber, whatKind, noOfPeople, area, originalSource, name, others, coloumnID;
    //The columns we'll include in the dictionary table
    public static final String COLUMN_ID = "_id";
    public static final String AIDAV_TIMESTAMP = "aidav_timestamp";
    public static final String AIDAV_CONT_NUMBER = "aidav_contnumber";
    public static final String AIDAV_WHATKIND = "aidav_whatkind";
    public static final String AIDAV_NO_OF_PEOPLE = "aidav_noofpeople";
    public static final String AIDAV_AREA = "aidav_area";
    public static final String AIDAV_ORIGINAL_SOURCE = "aidav_orignal_source";
    public static final String AIDAV_NAME = "aidav_name";
    public static final String AIDAV_OTHERS = "aidav_others";



    public static final String AIDAVAILABLE_TABLE = "aidavailabletable";

    // Database creation SQL statement

    private static final String AIDAVAILABLE_TABLE_CREATE = "create table "
            + AIDAVAILABLE_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + AIDAV_TIMESTAMP + " text null, "
            + AIDAV_CONT_NUMBER + " text null,"
            + AIDAV_WHATKIND + " text null,"
            + AIDAV_NO_OF_PEOPLE + " text null,"
            + AIDAV_AREA + " text null,"
            + AIDAV_ORIGINAL_SOURCE + " text null,"
            + AIDAV_NAME + " text null,"
            + AIDAV_OTHERS + " text null,"
            + " text null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(AIDAVAILABLE_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + AIDAVAILABLE_TABLE);
        onCreate(database);
    }

}
