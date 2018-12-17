package com.example.partybuilding.partybuilding.home.bean;

import java.util.List;

public class Home_CarouselmapBean {
    private List<CarouselmapBean> carouselmap;

    public List<CarouselmapBean> getCarouselmap() {
        return carouselmap;
    }

    public void setCarouselmap(List<CarouselmapBean> carouselmap) {
        this.carouselmap = carouselmap;
    }

    public static class CarouselmapBean {
        /**
         * imgurl : http://119.80.161.8:9998/FHBE/attachFiles/file/16pic_4942011_s.jpg
         */

        private String imgurl;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

//
//    /**
//     * imgurl : http://119.80.161.8:9998/FHBE/attachFiles/file/16pic_4942011_s.jpg
//     */
//
//    private String imgurl;
//
//    public String getImgurl() {
//        return imgurl;
//    }
//
//    public void setImgurl(String imgurl) {
//        this.imgurl = imgurl;
//    }
}
