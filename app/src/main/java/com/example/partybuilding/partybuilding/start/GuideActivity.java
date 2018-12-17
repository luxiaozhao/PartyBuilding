package com.example.partybuilding.partybuilding.start;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.partybuilding.partybuilding.MainActivity;
import com.example.partybuilding.partybuilding.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private static final int REQUEST_CODE_SETTING = 300;
    private ViewPager vp;
    private int []imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    private ViewGroup vg;//放置圆点
//start
    //实例化原点View
    private ImageView iv_point;
    private ImageView[]ivPointArray;

    //最后一页的按钮
    private ImageButton ib_start;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

//        init();

        sharedPreferences = getSharedPreferences("info2", MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        String result = sharedPreferences.getString("ss", null);
//        if (TextUtils.isEmpty(result)) {
//            //使用editor保存数据
//            editor.putString("ss", "1");
//            //注意一定要提交数据，此步骤容易忘记
//            editor.commit();
//        }else {
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        ib_start = (ImageButton) findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });

        //加载ViewPager
        initViewPager();

        //加载底部圆点
        //initPoint();

    }



    /**
     * 加载底部圆点
     */
    private void initPoint() {
        //这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0;i<size;i++){
            iv_point = new ImageView(this);
            iv_point.setLayoutParams(new ViewGroup.LayoutParams(100,100));
            iv_point.setPadding(50,0,50,0);//left,top,right,bottom
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0){
                //a,h改成小圆点
//                iv_point.setBackgroundResource(R.mipmap.a);
            }else{
                //      iv_point.setBackgroundResource(R.mipmap.h);
            }
            //将数组中的ImageView加入到ViewGroup
            vg.addView(ivPointArray[i]);
        }
    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.lunbo1,R.mipmap.lunbo1,R.mipmap.lunbo1,R.mipmap.lunbo1};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imageIdArray.length;
//        for (int i = 0;i<length;i++){
////       //     ivPointArray[position].setBackgroundResource(R.mipmap.a);
////            if (position != i){
////            //    ivPointArray[i].setBackgroundResource(R.mipmap.h);
////            }
////        }

        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            ib_start.setVisibility(View.VISIBLE);
        }else {
            ib_start.setVisibility(View.GONE);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


//    /*
//     * 动态权限
//     * */
//    private void init() {
//        EasyPermission.with(this).code(101).request();
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        EasyPermission.handleResult(this, requestCode, permissions, grantResults);//处理权限申请回调结果
//    }

    /**
     * 日历权限申请
     */
    private void calendarApply() {
        AndPermission.with(this)
                .permission(Permission.CALENDAR)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 相机权限申请
     */
    private void cameraApply() {
        AndPermission.with(this)
                .permission(Permission.CAMERA)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 通讯录权限申请
     */
    private void cantactsApply() {
        AndPermission.with(this)
                .permission(Permission.CONTACTS)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 位置权限申请
     */
    private void locationApply() {
        AndPermission.with(this)
                .permission(Permission.LOCATION)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 音频录制权限申请
     */
    private void microphoneApply() {
        AndPermission.with(this)
                .permission(Permission.MICROPHONE)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 电话权限申请
     */
    private void phoneApply() {
        AndPermission.with(this)
                .permission(Permission.PHONE)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 传感器权限申请
     */
    private void sensorsApply() {
        AndPermission.with(this)
                .permission(Permission.SENSORS)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 短信权限申请
     */
    private void smsApply() {
        AndPermission.with(this)
                .permission(Permission.SMS)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 外部存储权限申请
     */
    private void storageApply() {
        AndPermission.with(this)
                .permission(Permission.STORAGE)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }

    /**
     * 多个权限同时申请
     */
    private void moreApply() {
        AndPermission.with(this)
                .permission(Permission.CAMERA, Permission.STORAGE)
                .callback(permissionListener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(GuideActivity.this, rationale).show();
                    }
                })
                .start();
    }
    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            Toast.makeText(GuideActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(GuideActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(GuideActivity.this, REQUEST_CODE_SETTING).show();
            }
        }
    };
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);
//
//
//    }
//}
