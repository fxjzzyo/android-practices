package com.fxjzzyo.aidl_test.content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.fxjzzyo.aidl_test.Book;
import com.fxjzzyo.aidl_test.R;

public class ProviderActivity extends AppCompatActivity {
    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Uri bookUri = Uri.parse("content://com.fxjzzyo.aidl_test.book.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.d(TAG, "query book: " + book.toString());
        }
        bookCursor.close();


        Uri userUri = Uri.parse("content://com.fxjzzyo.aidl_test.book.provider/user");
        ContentValues userValues = new ContentValues();
        userValues.put("_id", 3);
        userValues.put("name", "fxjzzyo");
        userValues.put("sex", 0);
        getContentResolver().insert(userUri, userValues);
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.sex = userCursor.getInt(2) == 1;
            Log.d(TAG, "query user: " + user.toString());
        }
        userCursor.close();

    }
}
