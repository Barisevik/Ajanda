package com.example.myapplication;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class Contentprovedir extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.planvehatirlatici.Contentprovedir";
    static final String URL = "content://" + PROVIDER_NAME + "/planım";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String BASLIK = "baslik";
    static final String ICERIK = "icerik";
    static final String TARIH = "tarih";


    static final int Plan = 1;
    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "planim", Plan);
    }


    private static HashMap<String, String> PLAN_PROJECTION_MAP;

    //-------------------- Database ---------------------


    private SQLiteDatabase sqLiteDatabase;
    static final String PlanDatabase_NAME = "PLANIM";
    static final String PLAN_TABLE_NAME = "planim";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_PLAN_DATABASE = "CREATE TABLE "
            + PLAN_TABLE_NAME + "( baslik TEXT UNIQUE," + "icerik TEXT," + "tarih TEXT,"
            + "id  INTEGER );";


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, PlanDatabase_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_PLAN_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PROVIDER_NAME);
            onCreate(db);
        }
    }






    @Override
    public boolean onCreate() {
        Context context= getContext();
        DatabaseHelper databaseHelper= new DatabaseHelper(context);
        sqLiteDatabase =databaseHelper.getWritableDatabase();
        return sqLiteDatabase != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder= new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(PLAN_TABLE_NAME);
        switch (uriMatcher.match(uri)){
            case Plan:
                sqLiteQueryBuilder.setProjectionMap(PLAN_PROJECTION_MAP);
        }
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase,projection,selection,selectionArgs,null,null,null);

        cursor.setNotificationUri(getContext().getContentResolver(),uri);

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId =sqLiteDatabase.insert(PLAN_TABLE_NAME,null, values);
        if (rowId>0){
            Uri newuri= ContentUris.withAppendedId(CONTENT_URI,rowId);
            getContext().getContentResolver().notifyChange(newuri,null);

            return newuri;
        }


        throw new SQLException("Error!");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount=0;
        switch (uriMatcher.match(uri)){
            case Plan:
                rowCount=sqLiteDatabase.delete(PLAN_TABLE_NAME,selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Failed URI");

        }
        getContext().getContentResolver().notifyChange(uri,null );

        return rowCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount=0;
        switch (uriMatcher.match(uri)){
            case Plan:
                rowCount=sqLiteDatabase.update(PLAN_TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Failed URI");

        }
        getContext().getContentResolver().notifyChange(uri,null );

        return rowCount;

    }
}
