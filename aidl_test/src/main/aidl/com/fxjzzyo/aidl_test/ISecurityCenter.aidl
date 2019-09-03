// ISecurityCenter.aidl
package com.fxjzzyo.aidl_test;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    String encrypt(in String content);
    String decrypt(in String password);
}
