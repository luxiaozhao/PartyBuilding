package com.example.partybuilding.partybuilding.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.aboutus_return)
    ImageView aboutusReturn;
    @BindView(R.id.aboutus_content)
    TextView aboutusContent;

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int intiLayout() {
        return R.layout.activity_about_us;
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

//  遵循《中国共产党章程》、党内相关规章制度及管理办法,结合基层党建工作实际,应用云计算技术解决方案,按照“
//
//

        ButterKnife.bind(this);
        String content = "<font color=\"#000\">" + "  遵循《中国共产党章程》、党内相关规章制度及管理办法,结合基层党建工作实际,应用云计算技术解决方案,按照“" + "</font>"
                + "<font color=\"#fe0000\">" + "规范做事、有效做事" + "</font>"
                + "<font color=\"#000\">" + "”的理念,为党的各级基层组织、广大党员和培养对象提供的管理平台、工作平台、学习平台、信息平台,是“互联网+基层党建”的创新模式。" + "</font>";
        Spanned spanned = Html.fromHtml(content);


        aboutusContent.setText("      " + spanned);



    }

    @OnClick({R.id.aboutus_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aboutus_return:
                finish();
                break;

        }
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about_us);
//    }
}
