package com.example.partybuilding.partybuilding.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.R;

public class Notice_Fragment extends Fragment {

    public Notice_Fragment() {
    }

    private TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.item4, container, false);
        text = (TextView) view.findViewById(R.id.text4);

        text.setText("第四个界面");

        return  view;
    }

//    @Override
//    public void onResume() {
//
//        text.setText("zheshi 1333333333333的倍数");
//
//        Log.e("testFratment3","3333333333333333333d的倍数");
//        super.onResume();
//    }

}
