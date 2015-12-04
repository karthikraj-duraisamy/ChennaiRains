package net.karthikraj.apps.chennairains.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.karthikraj.apps.chennairains.models.AidAvailable;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class RestClientDBHelper extends SQLiteOpenHelper {

    public RestClientDBHelper(Context context) {
        super(context, RestClientDB.DATABASE_NAME, null, RestClientDB.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {

        ContactsTable.onCreate(database);
        AidAvailableTable.onCreate(database);
        AidNeededTable.onCreate(database);
        RescueTable.onCreate(database);
        SheltersTable.onCreate(database);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        ContactsTable.onUpgrade(database, oldVersion, newVersion);
        AidAvailableTable.onUpgrade(database, oldVersion, newVersion);
        AidNeededTable.onUpgrade(database, oldVersion, newVersion);
        RescueTable.onUpgrade(database, oldVersion, newVersion);
        SheltersTable.onUpgrade(database, oldVersion, newVersion);
    }
}

