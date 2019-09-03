package com.fxjzzyo.aidl_test.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fxjzzyo.aidl_test.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClientActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    mMessageTextView.setText(mMessageTextView.getText() + (String) msg.obj);
                    break;

                case MESSAGE_SOCKET_CONNECTED:
                    mSendButton.setEnabled(true);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpclient);
        mSendButton = findViewById(R.id.btn_send);
        mMessageEditText = findViewById(R.id.et_msg);
        mMessageTextView = findViewById(R.id.tv_msg);

        mSendButton.setOnClickListener(this);

        Intent service = new Intent(this, TCPServerService.class);
        startService(service);
        new Thread() {
            @Override
            public void run() {
                connectTcpServer();
            }
        }.start();

    }

    private void connectTcpServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect tcp server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                System.out.println("connect tcp server failed, retry...");
                e.printStackTrace();
            }
        }

        try {
            // 接收服务端消息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TCPClientActivity.this.isFinishing()) {
                String msg = in.readLine();
                System.out.println("receive : " + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server " + time + ":" + msg + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget();
                }
            }
            System.out.println("quit...");
            mPrintWriter.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("client error..."+e.getMessage());
        }


    }

    private String formatDateTime(long currentTimeMillis) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(currentTimeMillis);
        return format.format(d1);
    }

    @Override
    public void onClick(View v) {
        if(v == mSendButton){
            final String msg = mMessageEditText.getText().toString();
            if(!TextUtils.isEmpty(msg) && mPrintWriter!=null){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPrintWriter.println(msg);
                    }
                }).start();
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                String showedMsg = "self "+time+":"+msg+"\n";
                mMessageTextView.setText(mMessageTextView.getText()+showedMsg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onDestroy();
    }
}
