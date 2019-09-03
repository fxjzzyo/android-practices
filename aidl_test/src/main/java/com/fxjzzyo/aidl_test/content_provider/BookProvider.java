package com.fxjzzyo.aidl_test.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.PropertyResourceBundle;

public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider";

    public static final String AUTHORITY = "com.fxjzzyo.aidl_test.book.provider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    public BookProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG,"delete, current thread: "+Thread.currentThread());
        String table = getTableName(uri);
        if(table == null){
            throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
        int count = mDb.delete(table, selection, selectionArgs);
        if(count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public String getType(Uri uri) {
        Log.d(TAG,"getType, current thread: "+Thread.currentThread());
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG,"insert, current thread: "+Thread.currentThread());
        String table = getTableName(uri);
        if(table == null){
            throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
        mDb.insert(table,null,values);
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG,"onCreate, current thread: "+Thread.currentThread());
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        mDb.execSQL("delete from "+DbOpenHelper.BOOK_TABLE_NAME);
        mDb.execSQL("delete from "+DbOpenHelper.USER_TABLE_NAME);
        mDb.execSQL("insert into book values(3,'Android');");
        mDb.execSQL("insert into book values(4,'IOS');");
        mDb.execSQL("insert into book values(5,'html5');");
        mDb.execSQL("insert into user values(1,'jake',1);");
        mDb.execSQL("insert into user values(2,'jasmine',0);");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG,"query, current thread: "+Thread.currentThread());
        String table = getTableName(uri);
        if(table == null){
            throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
        return mDb.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG,"update, current thread: "+Thread.currentThread());
        String table = getTableName(uri);
        if(table == null){
            throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
        int count = mDb.update(table, values, selection, selectionArgs);
        if(count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (sUriMatcher.match(uri)){
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;

            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
                default:break;
        }
        return tableName;
    }

    @Nullable
    @Override
    public Bundle call(@NonNull String method, @Nullable String arg, @Nullable Bundle extras) {
        return super.call(method, arg, extras);
    }
}
