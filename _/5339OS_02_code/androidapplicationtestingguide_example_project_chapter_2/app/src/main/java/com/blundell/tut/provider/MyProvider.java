package com.blundell.tut.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

public class MyProvider extends ContentProvider {

    public static final String AUTHORITY = "com.blundell.tut.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static final String DB_NAME = "dummy.db";
    private static final int DB_VERSION = 1;
    private static final int DUMMY = 1;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH) {{
        addURI(AUTHORITY, "*", DUMMY);
    }};
    private static final HashMap<String, String> DUMMY_PROJECTION_MAP = new HashMap<String, String>() {{
        put("_id", "_id");
        put("name", "name");
    }};

    private DatabaseHelper databaseHelper;

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        switch (URI_MATCHER.match(uri)) {
            case DUMMY:
                return db.delete("dummy", where, whereArgs);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not supported - not used in this example");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not supported - not used in this example");
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseHelper = new DatabaseHelper(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (URI_MATCHER.match(uri)) {
            case DUMMY:
                qb.setTables("dummy");
                qb.setProjectionMap(DUMMY_PROJECTION_MAP);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not supported - not used in this example");
    }

    static final class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE dummy (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(255)" +
                    ")");

            db.execSQL("INSERT INTO dummy VALUES (1, 'Donald')");
            db.execSQL("INSERT INTO dummy VALUES (2, 'Mickey')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            throw new UnsupportedOperationException("Not supported - not used in this example");
        }

    }

}
