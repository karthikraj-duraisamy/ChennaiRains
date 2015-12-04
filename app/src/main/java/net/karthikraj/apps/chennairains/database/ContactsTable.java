package net.karthikraj.apps.chennairains.database;


import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class ContactsTable {




    //The columns we'll include in the dictionary table
    public static final String COLUMN_ID = "_id";
    public static final String CON_SERVICE = "con_service";
    public static final String CON_AREA = "con_area";
    public static final String CON_NAME = "con_name";
    public static final String CON_NUMBER = "con_number";
    public static final String CON_OTHERS = "con_others";
    public static final String CON_LAST_UPDATED = "con_last_updated";



    public static final String CONTACTS_TABLE = "contactstable";

    // Database creation SQL statement

    private static final String CONTACTS_TABLE_CREATE = "create table "
            + CONTACTS_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + CON_SERVICE + " text null, "
            + CON_AREA + " text null,"
            + CON_NAME + " text null,"
            + CON_NUMBER + " text null,"
            + CON_OTHERS + " text null,"
            + CON_LAST_UPDATED + " text null,"
            + " text null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CONTACTS_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE);
        onCreate(database);
    }

}
