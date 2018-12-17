package com.example.partybuilding.partybuilding.upcoming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.R;

public class Upcoming_Fragment extends Fragment {

    public Upcoming_Fragment() {
    }

    private TextView text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  =  inflater.inflate(R.layout.item2, container, false);


        text = (TextView) view.findViewById(R.id.text2);

        text.setText("这是第二个界面");
        return  view;

    }

//    @Override
//    public void onResume() {
//
//        text.setText("zheshi 22222222d的倍数");
//
//        Log.e("testFratment2","222222222222222222的倍数");
//        super.onResume();
//    }
}
