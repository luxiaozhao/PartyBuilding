package com.example.partybuilding.partybuilding.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.base.BaseActivity;
import com.example.partybuilding.partybuilding.disembark.LoginActivity;

public class SearchActivity extends BaseActivity {
    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//    }
}
