package net.karthikraj.apps.chennairains.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bm on 4/12/15.
 */
public class RescueTable {
    //timeStamp, contactNumber, noOfPeople, infantWomChildren, area, typeOfEmerg,
    // originalSource, severity, notes, latitude, longtitude, mapsURL, claimedBy,coloumnID;
    //The columns we'll include in the dictionary table
    public static final String COLUMN_ID = "_id";
    public static final String RES_TIMESTAMP = "res_timestamp";
    public static final String RES_CONTACT_NUMBER = "res_cont_number";
    public static final String RES_NO_OF_PEOPLE = "res_noof_people";
    public static final String RES_INFANTWOMEN = "res_infantwomen";
    public static final String RES_AREA = "res_area";
    public static final String RES_TYPEOFEMERG = "res_typeofemerg";
    public static final String RES_ORIG_SOURCE = "res_origsource";
    public static final String RES_SEVERITY = "res_severity";
    public static final String RES_NOTES = "res_notes";
    public static final String RES_LATITUDE = "res_latitude";
    public static final String RES_LONGITUDE = "res_longitude";
    public static final String RES_MAPSURL = "res_mapsurl";
    public static final String RES_CLAIMEDBY = "res_claimedby";



    public static final String RESCUE_TABLE = "rescuetable";

    // Database creation SQL statement

    private static final String RESCUE_TABLE_CREATE = "create table "
            + RESCUE_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + RES_TIMESTAMP + " text null, "
            + RES_CONTACT_NUMBER + " text null,"
            + RES_NO_OF_PEOPLE + " text null,"
            + RES_INFANTWOMEN + " text null,"
            + RES_AREA + " text null,"
            + RES_TYPEOFEMERG + " text null,"
            + RES_ORIG_SOURCE + " text null,"
            + RES_SEVERITY + " text null,"
            + RES_NOTES + " text null,"
            + RES_LATITUDE + " text null,"
            + RES_LONGITUDE + " text null,"
            + RES_MAPSURL + " text null,"
            + RES_CLAIMEDBY + " text null,"
            + " text null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(RESCUE_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + RESCUE_TABLE);
        onCreate(database);
    }

}
