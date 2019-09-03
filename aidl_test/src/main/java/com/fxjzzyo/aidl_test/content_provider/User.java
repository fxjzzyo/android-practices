package com.fxjzzyo.aidl_test.content_provider;

/**
 * @author fanlulin
 * @since 2019-08-29
 */
public class User {
    public int userId;
    public String userName;
    public boolean sex;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
