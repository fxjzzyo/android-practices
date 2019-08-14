package com.fxjzzyo.simpleimagetest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.fxjzzyo.simpleimagetest.util.ImageCompress;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView ivSrc, ivDst;
    private String imageName = "test.gif";
    public static String IMAGE_DIR = "MyImageDir";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:

                    break;
                case 1:
                    loadSrcImg();
                    break;
                case 2:
                    loadDstImg();
                    break;
                default:
                    break;
            }
        }
    };



    private void loadSrcImg() {
        File externalFilesDir = getExternalFilesDir(null);

        File imageFile = new File(externalFilesDir.getAbsolutePath()
                + File.separator + IMAGE_DIR + File.separator + imageName);
        Log.d("tagg","img--"+imageFile.getAbsolutePath());
//        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        Glide.with(this).load(imageFile).into(ivSrc);
    }

    private void loadDstImg(){
        File externalFilesDir = getExternalFilesDir(null);

        File imageFile = new File(externalFilesDir.getAbsolutePath()
                + File.separator + IMAGE_DIR + File.separator + imageName);
        String src = imageFile.getAbsolutePath();
        String dst = src.substring(0, src.lastIndexOf("/")) + File.separator + "dst_" + imageName;

        if (dst != null) {
            Glide.with(this).load(dst).into(ivDst);
        } else {
            Toast.makeText(this, "文件为空", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivDst = findViewById(R.id.iv_dst);
        ivSrc = findViewById(R.id.iv_src);

        initDatas();
    }

    private void initDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File externalFilesDir = getExternalFilesDir(null);
                File outDir = new File(externalFilesDir.getAbsolutePath(),IMAGE_DIR);
                if(!outDir.exists()) outDir.mkdirs();

                File outPath = new File(externalFilesDir.getAbsolutePath()
                        + File.separator + IMAGE_DIR + File.separator + imageName);
                Log.d("tagg","----out--"+outPath);

                FileOutputStream os = null;
                InputStream is = null;
                try {
                    if(!outPath.exists()){
                        outPath.createNewFile();
                    }
                    is = MainActivity.this.getAssets().open(imageName);
                    os = new FileOutputStream(outPath);
                    byte[] buffer = new byte[512];
                    int len = 0;
                    while ((len = is.read(buffer)) > 0) {
                        os.write(buffer, 0, len);
                    }
                    os.flush();
                    is.close();
                    os.close();
                    mHandler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("tagg","----out--"+e.getMessage());
                }
            }
        }).start();


    }

    /**
     * 转换gif 压缩
     *
     * @param view
     */
    public void trans(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                File externalFilesDir = getExternalFilesDir(null);
                String imageDir = "MyImageDir";
                File imageFile = new File(externalFilesDir.getAbsolutePath()
                        + File.separator + imageDir + File.separator + imageName);
                String src = imageFile.getAbsolutePath();
                Log.d("tagg","----src--"+src);
                ImageCompress imageCompress = new ImageCompress();
                // 压缩
                byte[] compressImg = imageCompress.compressImageDataWithSize(MainActivity.this, getImgByteData(src), 1024000);
                bytesToImageFile(compressImg);
                mHandler.sendEmptyMessage(2);

            }
        }).start();


    }

    private byte[] getImgByteData(String src) {
        Log.d("tagg","---getImgByteData-src---"+src);
        File file = new File(src);
        int byteSize = 1024;
        byte[] datas = new byte[byteSize];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(byteSize);
            int len = 0;
            while ((len = inputStream.read(datas)) > 0) {
                baos.write(datas, 0, len);
            }
            baos.flush();
            inputStream.close();
            baos.close();
            Log.d("tagg","---压缩前----"+baos.toByteArray().length);
            return baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tagg","---getImgByteData----"+e.getMessage());
        }
        return null;

    }

    private File bytesToImageFile(byte[] bytes) {
        Log.d("tagg","---dst----");
        if (bytes == null || bytes.length == 0) return null;
        Log.d("tagg","---dst2压缩后----"+bytes.length);
        File externalFilesDir = getExternalFilesDir(null);
        String imageDir = "MyImageDir";

        File imageFile = new File(externalFilesDir.getAbsolutePath()
                + File.separator + imageDir + File.separator + imageName);
        String src = imageFile.getAbsolutePath();
        String dst = src.substring(0, src.lastIndexOf("/")) + File.separator + "dst_" + imageName;
        Log.d("tagg","---dst----"+dst);
        try {
            File file = new File(dst);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tagg","---bytesToImageFile----"+e.getMessage());
        }
        return null;
    }

}
