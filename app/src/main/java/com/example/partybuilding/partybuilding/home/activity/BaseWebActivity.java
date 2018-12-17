package com.example.partybuilding.partybuilding.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.base.BaseActivity;
import com.example.partybuilding.partybuilding.home.adapter.ResultsFolderAdapter2;
import com.example.partybuilding.partybuilding.home.bean.Beanw;

import java.util.ArrayList;
import java.util.List;

public class BaseWebActivity extends BaseActivity {
    private TextView activity_web_text;
    private RecyclerView activity_web_recyclerview;
    private List<Beanw> beans;
    private ImageView img;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, BaseWebActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_base_web;
    }

    @Override
    public void initView() {

        activity_web_text = findViewById(R.id.activity_web_text);
        activity_web_recyclerview = findViewById(R.id.activity_web_recyclerview);

        img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getdata();
        String title= getIntent().getStringExtra("title");

        activity_web_text.setText(title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ResultsFolderAdapter2 dyuamicAdapter1 = new ResultsFolderAdapter2(this, beans);
        activity_web_recyclerview.setLayoutManager(linearLayoutManager);
        activity_web_recyclerview.setNestedScrollingEnabled(false);
        activity_web_recyclerview.setLayoutManager(new GridLayoutManager(this, 1));
        activity_web_recyclerview.setAdapter(dyuamicAdapter1);

    }

    @Override
    public void initData() {

    }

    private void getdata() {
        beans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Beanw bean1 = new Beanw();
            bean1.setStr("304所组织开展新版《中国共产党纪律处分条例》在线知识测试活动");
            bean1.setStr1("2018年11月2日");
            bean1.setStr2("党委新闻");
            beans.add(bean1);
        }
    }
}
