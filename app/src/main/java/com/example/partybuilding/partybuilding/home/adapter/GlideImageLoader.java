package com.example.partybuilding.partybuilding.home.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.partybuilding.partybuilding.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {






            try {
                Glide.with(context).load(path).into(imageView);
            }catch (Exception e){
                Glide.with(context).load(R.mipmap.lunbo1).into(imageView);
            }

//            Glide
//                    .with(context)
//                    .load(path)
//                    .placeholder(R.mipmap.lunbo1)
//                    .error(R.mipmap.lunbo1)
//                    .crossFade(1000)
//                    .into(imageView);

            //Glide 加载图片简单用法

//            Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).into(imageView);
            //用fresco加载图片简单用法，记得要写下面的createImageView方法

        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
////            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeVie simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
}