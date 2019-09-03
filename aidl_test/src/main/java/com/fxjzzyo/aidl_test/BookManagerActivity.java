package com.fxjzzyo.aidl_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.AtomicFile;
import android.util.Log;

import java.util.List;

public class BookManagerActivity extends AppCompatActivity {
    private static final String TAG = "BookManagerActivity";

    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager = bookManager;
                List<Book> bookList = bookManager.getBookList();
                Log.i(TAG,"query book list, list type: "+bookList.getClass().getCanonicalName());
                Log.i(TAG,"query book list: "+bookList.toString());

                Book newBook = new Book(3,"Android开发艺术探索");
                bookManager.addBook(newBook);
                Log.i(TAG,"add book: "+newBook);
                List<Book> newList = bookManager.getBookList();
                Log.i(TAG,"new query book list: "+newList.toString());
                bookManager.registerListener(mOnNewBookArrivedListener);
                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteBookManager = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        Intent intent = new Intent(this,BookManagerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if(mRemoteBookManager!=null && mRemoteBookManager.asBinder().isBinderAlive()){
            try {
                Log.i(TAG,"unregister listener: "+mOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        unbindService(mConnection);
        super.onDestroy();
    }

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED,newBook).sendToTarget();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }
    };


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG,"receive new book: "+msg.obj);
                    break;
                    default:
                        super.handleMessage(msg);
                        break;
            }
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mRemoteBookManager == null){
                return;
            }

            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient,0);
            mRemoteBookManager = null;
            // 重新绑定远程service
            Intent intent = new Intent(BookManagerActivity.this,BookManagerService.class);
            bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
        }
    };
}
