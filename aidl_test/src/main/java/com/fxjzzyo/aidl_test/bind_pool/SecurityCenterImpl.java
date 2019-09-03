package com.fxjzzyo.aidl_test.bind_pool;

import android.os.IBinder;
import android.os.RemoteException;

import com.fxjzzyo.aidl_test.ISecurityCenter;

/**
 * @author fanlulin
 * @since 2019-08-30
 */
public class SecurityCenterImpl extends ISecurityCenter.Stub {

    private static final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECRET_CODE;
        }

        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {

        return encrypt(password);
    }

}
