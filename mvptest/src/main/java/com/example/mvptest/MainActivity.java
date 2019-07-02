package com.example.mvptest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvptest.model.Phone;
import com.example.mvptest.presenter.IMvpMainView;
import com.example.mvptest.presenter.iml.MainPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMvpMainView {

    private EditText input_phone;
    private Button btn_search;
    private TextView result_phone, result_province, result_type, result_carrier;

    private MainPresenter mainPresenter;
    private ProgressDialog progressDialog;

    private LinearLayout mShowInfoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        input_phone = (EditText) findViewById(R.id.input_phone);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
        result_phone = (TextView) findViewById(R.id.result_phone);
        result_province = (TextView) findViewById(R.id.result_province);
        result_type = (TextView) findViewById(R.id.result_type);
        result_carrier = (TextView) findViewById(R.id.result_carrier);
        mShowInfoLayout = (LinearLayout) findViewById(R.id.id_activity_main_result_info_show_ll);

        mainPresenter = new MainPresenter(this);
        mainPresenter.attach(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:{
                mainPresenter.searchPhoneInfo(input_phone.getText().toString());
            }
            break;
            default:
                break;
        }
    }


    //MvpMainView接口方法
    @Override
    public void showToash(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateView() {
        Phone phone = mainPresenter.getPhoneInfo();
        if (phone != null){
            mShowInfoLayout.setVisibility(View.VISIBLE);
            result_phone.setText("手机号：" + phone.getTelString());
            result_province.setText("省份：" + phone.getProvince());
            result_type.setText("运营商：" + phone.getCatName());
            result_carrier.setText("归属运营商：" + phone.getCarrier());
        }else{
            mShowInfoLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void showLoading() {
        if (progressDialog == null){
            progressDialog = ProgressDialog.show(this, "", "正在加载", true,false);
        }else if (progressDialog.isShowing()){
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载...");
        }
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}
