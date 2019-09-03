package com.fxjzzyo.aidl_test.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServerService extends Service {

    private boolean mIsServiceDestroyed = false;

    private String[] mDefinedMessages = new String[]{
            "今天天气不错哦",
            "你知道吗？我可是可以和多个人同时聊天的哦",
            "给你讲个笑话吧：据说爱笑的人运气不会太差"
    };

    public TCPServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;

            try {
                // 监听本地8688端口
                serverSocket = new ServerSocket(8688);


            } catch (IOException e) {
                System.out.println("establish tcp server failed, port:8688");

                e.printStackTrace();
                return;
            }
            while (!mIsServiceDestroyed) {
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // 接收客户端消息
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        // 回复客户端消息

        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream())),true);
        out.println("欢迎来到聊天室！");

        while (!mIsServiceDestroyed){
            String str = in.readLine();
            System.out.println("msg from client: "+str);
            if(str == null){
                //客户端断开链接
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("send: "+msg);
        }

        System.out.println("mclient quit ");
        // 关闭流
        out.close();
        in.close();
        client.close();

    }
}
