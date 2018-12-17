package com.example.partybuilding.partybuilding.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.base.BaseActivity;
import com.example.partybuilding.partybuilding.common.Contants;
import com.example.partybuilding.partybuilding.common.URLConstant;
import com.example.partybuilding.partybuilding.disembark.bean.SuccessBean;
import com.example.partybuilding.partybuilding.mine.bean.amendPasswordBean;
import com.example.partybuilding.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.changepasswordactivity_used_password)
    EditText changepasswordactivityUsedPassword;
    @BindView(R.id.changepasswordactivity_fresh_password)
    EditText changepasswordactivityFreshPassword;
    @BindView(R.id.changepasswordactivity_confirm_modify)
    TextView changepasswordactivityConfirmModify;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int intiLayout() {
        return R.layout.activity_change_password;
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
    }

    @OnClick({R.id.changepasswordactivity_confirm_modify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changepasswordactivity_confirm_modify:
                submit();
                SuccessBean successBean = Hawk.get(Contants.loginInformation);
                String pid = successBean.getPid();
                String sid = successBean.getSid();
                Log.e("TAG","pid"+pid+"sid"+sid);
                OkHttpUtils
                        .get()
                        .url(URLConstant.CHANGEPASSWORD)
                        .addParams("sid", successBean.getSid())
                        .addParams("pid", successBean.getPid())
                        .addParams("oldpassword", changepasswordactivityUsedPassword.getText().toString())//旧
                        .addParams("newpassword", changepasswordactivityFreshPassword.getText().toString())//新
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                Log.e("TAG", "这是失败的方法" + request);
                            }

                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                amendPasswordBean bean = gson.fromJson(response, amendPasswordBean.class);
                                if (bean.isCode()) {
                                    ToastUtils.getInstance().showTextToast(ChangePasswordActivity.this, bean.getMsg());
                                    finish();
                                } else {
                                    ToastUtils.getInstance().showTextToast(ChangePasswordActivity.this, bean.getMsg());
                                }
                            }
                        });

                break;
        }
    }

    /**
     * 判断必填项是否为空
     */
    private void submit() {

        if (TextUtils.isEmpty(changepasswordactivityUsedPassword.getText().toString())) {
            Toast.makeText(this, "旧密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(changepasswordactivityFreshPassword.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_password);
//    }
}
