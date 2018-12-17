package com.example.partybuilding.partybuilding.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.common.Contants;
import com.example.partybuilding.partybuilding.common.URLConstant;
import com.example.partybuilding.partybuilding.disembark.LoginActivity;
import com.example.partybuilding.partybuilding.disembark.bean.SuccessBean;
import com.example.partybuilding.partybuilding.home.activity.BaseWebActivity;
import com.example.partybuilding.partybuilding.home.activity.SearchActivity;
import com.example.partybuilding.partybuilding.home.adapter.FolderAdapter;
import com.example.partybuilding.partybuilding.home.adapter.GlideImageLoader;
import com.example.partybuilding.partybuilding.home.adapter.NoticeAdapter;
import com.example.partybuilding.partybuilding.home.bean.AttestBean;
import com.example.partybuilding.partybuilding.home.bean.Bean;
import com.example.partybuilding.partybuilding.home.bean.Bww;
import com.example.partybuilding.partybuilding.home.bean.Home_CarouselmapBean;
import com.example.partybuilding.partybuilding.home.bean.Home_noticeBean;
import com.example.partybuilding.partybuilding.start.GuidePageAdapter;
import com.example.partybuilding.partybuilding.utils.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Home_Fragment extends Fragment {
    @BindView(R.id.denglu)
    LinearLayout denglu;
    @BindView(R.id.banner)
    Banner banner;
    //    @BindView(R.id.main_recycler)
//    RecyclerView mainRecycler;
    @BindView(R.id.home_notice)
    LinearLayout homeNotice;
    @BindView(R.id.home_news_recycler)
    RecyclerView homeNewsRecycler;
    Unbinder unbinder;
    @BindView(R.id.home_news_more)
    LinearLayout homeNewsMore;

    @BindView(R.id.home_scanit)
    LinearLayout homescanit;


    @BindView(R.id.home_search)
    LinearLayout home_search;

    @BindView(R.id.home_notice_name)
    TextView home_notice_name;



    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.guide_ll_point)
    LinearLayout guideLlPoint;


    private RecyclerView main_recycler;
    private RecyclerView home_news_recycler;
    private List<Bean> beans;
    private List<Bww> bwws = new ArrayList<>();
    private List<Home_CarouselmapBean.CarouselmapBean> carousel_urllist = new ArrayList<>();
    private View view;
    private FolderAdapter folderAdapter;
    private NoticeAdapter folderAdapter1;
    private SuccessBean successBean=new SuccessBean();

    public Home_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment_layout, container, false);//home_fragment_layout
        main_recycler = view.findViewById(R.id.main_recycler);
        home_news_recycler = view.findViewById(R.id.home_news_recycler);
        getdata();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        folderAdapter = new FolderAdapter(getActivity(), beans);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        main_recycler.setLayoutManager(manager);

        main_recycler.setNestedScrollingEnabled(false);

        main_recycler.setAdapter(folderAdapter);
        getcarouselmapurl();
        gethomeNotification();
        folderAdapter.setOnClickLinstener(new FolderAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                BaseWebActivity.start(getActivity(), beans.get(position).getString());
            }
        });
        getdata();
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        folderAdapter1 = new NoticeAdapter(getActivity(), bwws);
        home_news_recycler.setLayoutManager(linearLayoutManager1);
        home_news_recycler.setNestedScrollingEnabled(false);
        home_news_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        home_news_recycler.setAdapter(folderAdapter1);

        folderAdapter1.setOnClickLinstener(new NoticeAdapter.onClickLinstener() {
            @Override
            public void setOnClick(View view, int position) {
                ToastUtils.getInstance().showTextToast(getActivity(), "点击的是" + position + "条目");
            }
        });
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void gethomeNotification() {
        if (Hawk.contains(Contants.loginInformation)){
            successBean = Hawk.get(Contants.loginInformation);
            OkHttpUtils
                    .get()
                    .url(URLConstant.REVERIFICATION)
                    .addParams("sid", successBean.getSid())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e("TAG", "这是失败的方法" + request);
                        }

                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            AttestBean bean = gson.fromJson(response, AttestBean.class);
                            if (bean.isCode()){
                                getnotice();
                            }
                        }
                    });
        }







    }

    private void getnotice() {
        try {
            OkHttpUtils
                    .get()
                    .url(URLConstant.HOMENOTIFICATION)
                    .addParams("sid",successBean.getSid())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            home_notice_name.setText("暂无通知");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                Gson gson = new Gson();
                                Home_noticeBean bean = gson.fromJson(response, Home_noticeBean.class);
                                home_notice_name.setText(bean.getTitle());

                            }catch (Exception e){
                                home_notice_name.setText("暂无通知");
                            }


                        }
                    });
        }catch (Exception e){
            home_notice_name.setText("暂无通知");
        }



    }


    private void getdata() {
        bwws.clear();
        for (int i = 0; i < 4; i++) {
            Bww bww = new Bww();
            bww.setName("按实际库存你上课经常看见和出口市场卡死啊飒飒阿萨飒飒啊飒飒");
            bww.setShijian("2018年12月12日");
            bwws.add(bww);
        }

        beans = new ArrayList<>();
        Bean bean1 = new Bean();
        bean1.setString("中央精神");
        bean1.setImg(R.mipmap.r11);

        Bean bean2 = new Bean();
        bean2.setString("党组声音");
        bean2.setImg(R.mipmap.r22);

        Bean bean3 = new Bean();
        bean3.setString("党委新闻");
        bean3.setImg(R.mipmap.r33);

        Bean bean4 = new Bean();
        bean4.setString("基层交流");
        bean4.setImg(R.mipmap.r44);

        Bean bean5 = new Bean();
        bean5.setString("学习园地");
        bean5.setImg(R.mipmap.r55);
//        http://119.80.161.8:9999/FHBE/mobile/mobileNews/mobileNews/search.ht?param=&pageSize=2&pageIndex=2
        Bean bean6 = new Bean();
        bean6.setString("党务知识");
        bean6.setImg(R.mipmap.g66);


        Bean bean7 = new Bean();
        bean7.setString("在线学习");
        bean7.setImg(R.mipmap.r77);

        Bean bean8 = new Bean();
        bean8.setString("缴纳党费");
        bean8.setImg(R.mipmap.r88);

               beans.add(bean1);
        beans.add(bean2);
        beans.add(bean3);
        beans.add(bean4);
        beans.add(bean5);
        beans.add(bean6);
        beans.add(bean7);
        beans.add(bean8);
//        beans.add(bean1);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.denglu, R.id.home_notice, R.id.home_news_more, R.id.home_scanit, R.id.home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu:
                LoginActivity.start(getActivity());
                break;
            case R.id.home_notice:
                ToastUtils.getInstance().showTextToast(getActivity(), "我点击了通知");
                break;
            case R.id.home_news_more:
                ToastUtils.getInstance().showTextToast(getActivity(), "我点击了更多");
                break;
            case R.id.home_scanit:
                ToastUtils.getInstance().showTextToast(getActivity(), "此功能暂不开发");
                break;
            case R.id.home_search:
                SearchActivity.start(getActivity());
                break;
        }
    }

    /*
     * http://ip:9999/FHBE/personinfo.jsp
     *
     * */

    /*
     * 轮播图
     * */
    private void getcarouselmapurl() {
        OkHttpUtils
                .get()
                .url(URLConstant.HOME_CAROUSELMAP)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("TAG", "这是失败的方法" + request);
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        String responses = "{carouselmap:" + response + "}";
                        Home_CarouselmapBean bean = gson.fromJson(responses, Home_CarouselmapBean.class);
                        carousel_urllist.addAll(bean.getCarouselmap());


/*  //替换文本
        String s3=s;
        s3=s3.replaceAll("om", "en");
        System.out.println(s3);*/

                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < carousel_urllist.size(); i++) {

                            String imgurl = carousel_urllist.get(i).getImgurl();

                            String s3 = imgurl.replaceAll("9998", "9999");

                            strings.add(s3);
                        }


                        //        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //        //设置图片集合
                        banner.setImages(strings);
                        //        //banner设置方法全部调用完毕时最后调用
//                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.start();
                    }
                });
    }



    /**
     * 加载底部圆点
     */
//    private void initPoint() {
//        //这里实例化LinearLayout
////        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
//        //根据ViewPager的item数量实例化数组
//        ivPointArray = new ImageView[viewList.size()];
//        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
//        int size = viewList.size();
//        for (int i = 0;i<size;i++){
//            iv_point = new ImageView(this);
//            iv_point.setLayoutParams(new ViewGroup.LayoutParams(20,20));
//            iv_point.setPadding(30,0,30,0);//left,top,right,bottom
//            ivPointArray[i] = iv_point;
//            //第一个页面需要设置为选中状态，这里采用两张不同的图片
//            if (i == 0){
//                iv_point.setBackgroundResource(R.mipmap.full_holo);
//            }else{
//                iv_point.setBackgroundResource(R.mipmap.empty_holo);
//            }
//            //将数组中的ImageView加入到ViewGroup
//            guideLlPoint.addView(ivPointArray[i]);
//        }
//
//
//
//    }

    /**
     * 加载图片ViewPager
     */
//    private void initViewPager() {
//        vp = (ViewPager) findViewById(R.id.guide_vp);
//        //实例化图片资源
//        imageIdArray = new int[]{R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3};
//        viewList = new ArrayList<>();
//        //获取一个Layout参数，设置为全屏
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//
//        //循环创建View并加入到集合中
//        int len = imageIdArray.length;
//        for (int i = 0;i<len;i++){
//            //new ImageView并设置全屏和图片资源
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(params);
//            imageView.setBackgroundResource(imageIdArray[i]);
//
//            //将ImageView加入到集合中
//            viewList.add(imageView);
//        }
//
//        //View集合初始化好后，设置Adapter
//        guideVp.setAdapter(new GuidePageAdapter(viewList));
//        //设置滑动监听
//        guideVp.setOnPageChangeListener(this);
//    }


}