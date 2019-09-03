// IBinderPool.aidl
package com.fxjzzyo.aidl_test;

import android.os.IBinder;
// Declare any non-default types here with import statements

interface IBinderPool {
   IBinder queryBinder(int binderCode);
}
