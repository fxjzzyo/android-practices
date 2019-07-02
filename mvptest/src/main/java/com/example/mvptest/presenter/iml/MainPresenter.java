package com.example.mvptest.presenter.iml;

import com.example.mvptest.business.HttpUtil;
import com.example.mvptest.model.Phone;
import com.example.mvptest.presenter.IMvpMainView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanlulin on 2019-07-02.
 */

public class MainPresenter extends BasePresenter{
    private IMvpMainView mvpMainView;
    private String mUrl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";

    private Phone mPhone;

    public Phone getPhoneInfo(){
        return mPhone;
    }


    public  MainPresenter(IMvpMainView mainView){
        mvpMainView = mainView;
    }

    public void searchPhoneInfo(String phone){
        if (phone.length() != 11){
            mvpMainView.showToash("请输入正确的手机号");
            return;
        }
        mvpMainView.showLoading();

        //http请求的处理逻辑
        sendHttp(phone);

    }

    private void sendHttp(String phone) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("tel", phone);
        HttpUtil httpUntil = new HttpUtil(new HttpUtil.HttpResonse() {
            @Override
            public void onSuccess(Object object) {
                String json = object.toString();
                int index = json.indexOf("{");
                json = json.substring(index, json.length());

                //JsonObject
//                mPhone = parseModelWithOrgJson(json);
                //Gson
                mPhone = parseModelWithGson(json);
                //FastJson
//                mPhone = parseModelWithFastJson(json);
                mvpMainView.hideLoading();
                mvpMainView.updateView();
            }

            @Override
            public void onFail(String error) {
                mvpMainView.showToash(error);
                mvpMainView.hideLoading();
            }
        });
        httpUntil.sendGetHttp(mUrl, map);
    }

    public Phone parseModelWithGson(String json) {

        Gson gson = new Gson();
        Phone phone = gson.fromJson(json, Phone.class);

        return phone;
    }

    public Phone parseModelWithFastJson(String json) {
        Phone phone = JSONObject.parseObject(json, Phone.class);

        return phone;
    }

    public Phone parseModelWithOrgJson(String json) {
        Phone phone = new Phone();
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
            String value = jsonObject.getString("telString");
            phone.setTelString(value);

            value = jsonObject.getString("province");
            phone.setProvince(value);

            value = jsonObject.getString("catName");
            phone.setCatName(value);

            value = jsonObject.getString("carrier");
            phone.setCarrier(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return phone;
    }
}
