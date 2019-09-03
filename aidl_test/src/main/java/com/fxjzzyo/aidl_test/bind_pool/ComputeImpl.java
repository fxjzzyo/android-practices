package com.fxjzzyo.aidl_test.bind_pool;

import android.os.RemoteException;

import com.fxjzzyo.aidl_test.ICompute;

/**
 * @author fanlulin
 * @since 2019-08-30
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
