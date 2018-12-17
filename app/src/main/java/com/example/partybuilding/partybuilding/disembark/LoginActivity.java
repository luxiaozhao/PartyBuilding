package com.example.partybuilding.partybuilding.disembark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.partybuilding.partybuilding.common.Contants;
import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.common.URLConstant;
import com.example.partybuilding.partybuilding.base.BaseActivity;
import com.example.partybuilding.partybuilding.disembark.bean.SuccessBean;
import com.example.partybuilding.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_wisdom)
    TextView loginWisdom;
    @BindView(R.id.login_accountNumber)
    EditText loginAccountNumber;
    @BindView(R.id.login_accountNumber_lainear)
    LinearLayout loginAccountNumberLainear;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_password_lainear)
    LinearLayout loginPasswordLainear;
    @BindView(R.id.login_remember_password)
    LinearLayout loginRememberPassword;
    @BindView(R.id.login_landed)
    TextView loginLanded;
    @BindView(R.id.login_backtrack)
    TextView loginBacktrack;
    @BindView(R.id.login_remember_img)
    ImageView loginRememberImg;
    private String accountNumber;
    private String password;
    private boolean aBoolean = true;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        String str = "CHINA AEROSPACE SCIENCE & INDUSTRY CORP";
        if (Hawk.contains(Contants.rememberAccountAndPassword)) {
            Map<String, String> stringStringMap = Hawk.get(Contants.rememberAccountAndPassword);
            loginAccountNumber.setText(stringStringMap.get("accountNumber"));
            loginPassword.setText(stringStringMap.get("password"));
//            ToastUtils.getInstance().showTextToast(LoginActivity.this,"记住密码");
            Glide.with(LoginActivity.this).load(R.mipmap.remember2).into(loginRememberImg);
            aBoolean = false;
        }
        getClick();
    }

    private void getClick() {
        loginLanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountNumber = loginAccountNumber.getText().toString().trim();
                password = loginPassword.getText().toString().trim();

                if (!aBoolean) {
                    if (Hawk.contains(Contants.rememberAccountAndPassword)) {
                        Hawk.delete(Contants.rememberAccountAndPassword);
                    }
                    Map<String, String> stringStringMap = new HashMap<>();
                    stringStringMap.put("accountNumber", accountNumber);
                    stringStringMap.put("password", password);
                    Hawk.put(Contants.rememberAccountAndPassword, stringStringMap);
                } else {
                    Hawk.delete(Contants.rememberAccountAndPassword);
                }

                geturl();
            }
        });

        loginRememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (aBoolean) {
                    ToastUtils.getInstance().showTextToast(LoginActivity.this, "记住密码");
                    Glide.with(LoginActivity.this).load(R.mipmap.remember2).into(loginRememberImg);
                    aBoolean = false;
                } else {
                    ToastUtils.getInstance().showTextToast(LoginActivity.this, "忘记密码");
                    Glide.with(LoginActivity.this).load(R.mipmap.remember).into(loginRememberImg);
                    aBoolean = true;
                }

            }
        });


        loginBacktrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void geturl() {

        submit();
        OkHttpUtils
                .get()
                .url(URLConstant.SEND_MESSAGE)//    http://119.80.161.8:9999/FHBE/login.ht?username=djys&password=2
                .addParams("username", accountNumber)
                .addParams("password", password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("TAG", "");
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            SuccessBean successBean = gson.fromJson(response, SuccessBean.class);
                            String msg = successBean.getMsg();

                            ToastUtils.getInstance().showTextToast(LoginActivity.this, msg);
                            Log.e("TAG", "msg:" + msg);

                            if (successBean.isSuccess()) {
                                Hawk.put(Contants.loginInformation, successBean);
                                finish();
                            }

                        } catch (Exception e) {
                            Log.e("TAG", "失败:" + e.toString());
                        }
                    }
                });


    }

    /**
     * 判断必填项是否为空
     */
    private void submit() {

        if (TextUtils.isEmpty(accountNumber)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

    }


}
