package com.fxjzzyo.aidl_test.bind_pool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.fxjzzyo.aidl_test.ICompute;
import com.fxjzzyo.aidl_test.ISecurityCenter;
import com.fxjzzyo.aidl_test.R;

public class BindPoolActivity extends AppCompatActivity {

    private static final String TAG = "BindPoolActivity";

    private ISecurityCenter mSecrityCenter;
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_pool);
        new Thread(new TestTask()).start();

    }

    public class TestTask implements Runnable {

        @Override
        public void run() {
            doWork();
        }
    }

    private void doWork() {
        BinderPool binderPool = BinderPool.getInstance(BindPoolActivity.this);
        try {
            IBinder security = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);

            mSecrityCenter = SecurityCenterImpl.asInterface(security);
            Log.d(TAG, "visit ISecurityCenter");

            String msg = "helloworld-安卓";
            System.out.println("content:" + msg);

            String password = mSecrityCenter.decrypt(msg);
            System.out.println("encrypt:" + password);
            System.out.println("decrypt:" + mSecrityCenter.decrypt(password));

            Log.d(TAG, "visit ICompute");
            IBinder compute = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
            mCompute = ComputeImpl.asInterface(compute);

            System.out.println("3+5 = " + mCompute.add(3, 5));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
