package net.karthikraj.apps.chennairains.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Karthikraj Duraisamy on 4/12/15.
 */
public class AidAvailableContentProvider extends ContentProvider {

    // database
    private RestClientDBHelper database;

    // used for the UriMacher
    private static final int URI_MATCHER = 1;
    private static final int URI_MATCHER_ID = 2;

    private static final String AUTHORITY = "net.karthikraj.apps.chennairains.database.aidavailablecontentprovider";

    private static final String BASE_PATH_AIDAVAILABLE = "aidavailable";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH_AIDAVAILABLE);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/aidavailable";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/aidavailabletable";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_AIDAVAILABLE, URI_MATCHER);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_AIDAVAILABLE + "/#", URI_MATCHER_ID);
    }

    @Override
    public boolean onCreate() {
        database = new RestClientDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(AidAvailableTable.AIDAVAILABLE_TABLE);


        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case URI_MATCHER:
                break;
            case URI_MATCHER_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(AidAvailableTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());

                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {


        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case URI_MATCHER:
                // Zero count means empty table.
                id = sqlDB.insert(AidAvailableTable.AIDAVAILABLE_TABLE, null, values);

                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH_AIDAVAILABLE + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case URI_MATCHER:
                rowsDeleted = sqlDB.delete(AidAvailableTable.AIDAVAILABLE_TABLE, selection,
                        selectionArgs);
                break;
            case URI_MATCHER_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(AidAvailableTable.AIDAVAILABLE_TABLE,
                            AidAvailableTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(AidAvailableTable.AIDAVAILABLE_TABLE,
                            AidAvailableTable.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case URI_MATCHER:
                rowsUpdated = sqlDB.update(AidAvailableTable.AIDAVAILABLE_TABLE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case URI_MATCHER_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(AidAvailableTable.AIDAVAILABLE_TABLE,
                            values,
                            AidAvailableTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(AidAvailableTable.AIDAVAILABLE_TABLE,
                            values,
                            AidAvailableTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = {
                AidAvailableTable.COLUMN_ID, AidAvailableTable.AIDAV_OTHERS, AidAvailableTable.AIDAV_NAME, AidAvailableTable.AIDAV_ORIGINAL_SOURCE,
                AidAvailableTable.AIDAV_AREA, AidAvailableTable.AIDAV_CONT_NUMBER, AidAvailableTable.AIDAV_NO_OF_PEOPLE, AidAvailableTable.AIDAV_TIMESTAMP, AidAvailableTable.AIDAV_WHATKIND};
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}