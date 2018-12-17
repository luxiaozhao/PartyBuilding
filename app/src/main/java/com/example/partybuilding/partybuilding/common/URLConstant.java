package com.example.partybuilding.partybuilding.common;

public class URLConstant {


    /**
     * 服务器地址
     */

    public static final String BASE_URL = "http://119.80.161.8:9999/FHBE/";

    /*
     *  需要用到sid和pid的时候都需要验证一遍，防止过期（git请求）
     * */
    public static final String REVERIFICATION = BASE_URL + "valsession.ht";

    /**
     * 登陆
     */

    public static final String SEND_MESSAGE = BASE_URL + "login.ht";

    /**
     * 轮播图
     */

    public static final String HOME_CAROUSELMAP = BASE_URL + "mobile/carousel/carousel/carousel.ht";



/*
http://119.80.161.8:9999/FHBE/valsession.ht?sid=767CB82047E429AC5DAEA411B91D913F*/


    /*
     * 修改密码
     * */
    public static final String CHANGEPASSWORD = BASE_URL + "platform/system/sysUser/uppwd.ht";

//  http://119.80.161.8:9999/FHBE/platform/system/sysUser/uppwd.ht?oldpassword=2&newpassword=2&pid=430111197811111111&sid=D2846C3BB7D2FED4E482548A40A52933


//    public static final String BASE_URL = "http://192.168.1.207:8080/";//任老师的接口调试专用


    /**
     * 图片服务器地址
     */
    //public static final String BASE_PIC_URL = "http://154.8.214.63/";
//    public static final String BASE_PIC_URL = "http://101.36.138.100/"; //测试


    //图片网址
    public static final String BASE_PIC_URL = "http://yky.eplugger.cn/";

//    http://yky.eplugger.cn:8080/auth/news


    /**
     * 获取区号:
     * Area_number
     */
    public static final String AREA_NUMBER = "auth/dictionary?category=30&pages=1&pagesize=500";

    /*http://101.36.138.100:8080/auth/sendMessage?mobile=17801190741&type=0&domain=86*/


    /**
     * 验证码接口参数 type = 1 用户注册
     */
    public static final String SEND_MESSAGE_PARAM_TYPE_REGISTER = "1";

    /**
     * 登录
     * post
     */
    public static final String USR_LOGIN = "http://119.80.161.8:9999/FHBE/login.ht?";

    /**
     * 注册
     * post
     */
    public static final String USR_REGISTER = "auth/register";


    /**
     * 轮播图
     * pages页号
     * pagesize页条目数
     */
    public static final String APP_ADVERT = "auth/advert";




    /**
     * 首页通知
     */

    public static final String HOMENOTIFICATION = BASE_URL + "mobile/mobileNews/mobileNews/promulgate.ht";
//  http://119.80.161.8:9999/FHBE/mobile/mobileNews/mobileNews/promulgate.ht?sid=9014816C7A3BFDBACE793C975142EFDC

}
