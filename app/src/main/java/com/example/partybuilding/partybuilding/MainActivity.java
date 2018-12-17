package com.example.partybuilding.partybuilding;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.example.partybuilding.partybuilding.home.Home_Fragment;
import com.example.partybuilding.partybuilding.mine.Mine_Fragment;
import com.example.partybuilding.partybuilding.notice.Notice_Fragment;
import com.example.partybuilding.partybuilding.upcoming.Upcoming_Fragment;
import com.orhanobut.hawk.Hawk;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private FragmentTransaction fragmentTransaction;
    private Home_Fragment home_Fragment;
    private Upcoming_Fragment upcoming_Fragment;
    private FragmentManager supportFragmentManager;
    private Notice_Fragment notice_Fragment;
    private Mine_Fragment mine_Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Hawk.init(MainActivity.this).build();
//        setStatusBarFullTransparent();



        home_Fragment = new Home_Fragment();
        upcoming_Fragment = new Upcoming_Fragment();
        notice_Fragment = new Notice_Fragment();
        mine_Fragment = new Mine_Fragment();
        supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.conta, home_Fragment, "0")
                .add(R.id.conta, upcoming_Fragment, "2")
                .add(R.id.conta, notice_Fragment, "3")
                .add(R.id.conta, mine_Fragment, "4")
                .commit();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.main_home);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {//把图片注释掉就好了
            case R.id.main_home:
                supportFragmentManager.beginTransaction()
                        .hide(mine_Fragment)
                        .hide(notice_Fragment)
                        .hide(upcoming_Fragment)
                        .show(home_Fragment).commit();

                break;
            case R.id.main_upcoming:
                supportFragmentManager.beginTransaction()
                        .hide(home_Fragment)
                        .hide(notice_Fragment)
                        .hide(mine_Fragment)
                        .show(upcoming_Fragment).commit();
                break;
            case R.id.main_notice:
                supportFragmentManager.beginTransaction()
                        .hide(upcoming_Fragment)

                        .hide(mine_Fragment)
                        .hide(home_Fragment)
                        .show(notice_Fragment).commit();
                break;

            case R.id.main_mine:
                supportFragmentManager.beginTransaction()
                        .hide(upcoming_Fragment)
                        .hide(notice_Fragment)
                        .hide(home_Fragment)
                        .show(mine_Fragment).commit();
                break;
        }

    }
    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
