package com.example.partybuilding.partybuilding.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.common.Contants;
import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.base.BasessFragment;
import com.example.partybuilding.partybuilding.disembark.bean.SuccessBean;
import com.example.partybuilding.partybuilding.mine.activity.AboutUsActivity;
import com.example.partybuilding.partybuilding.mine.activity.ChangePasswordActivity;
import com.example.partybuilding.partybuilding.utils.ToastUtils;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Mine_Fragment extends BasessFragment {
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_position)
    TextView minePosition;
    Unbinder unbinder;
    @BindView(R.id.mine_data)
    LinearLayout mineData;
    @BindView(R.id.mine_password)
    LinearLayout minePassword;
    @BindView(R.id.mine_course)
    LinearLayout mineCourse;
    @BindView(R.id.mine_examination)
    LinearLayout mineExamination;
    @BindView(R.id.mine_about)
    LinearLayout mineAbout;
    @BindView(R.id.mine_cache)
    LinearLayout mineCache;
    @BindView(R.id.mine_signout)
    TextView mineSignout;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        try {
            SuccessBean successBean = Hawk.get(Contants.loginInformation);

            mineName.setText(successBean.getUsername());

            minePosition.setText(successBean.getMsg());
        } catch (Exception e) {
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment_layout;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        try {
            SuccessBean successBean = Hawk.get(Contants.loginInformation);

            mineName.setText(successBean.getUsername());

            minePosition.setText(successBean.getOrg());
        } catch (Exception e) {
        }


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_name, R.id.mine_position,R.id.mine_data, R.id.mine_password, R.id.mine_course, R.id.mine_examination, R.id.mine_about, R.id.mine_cache, R.id.mine_signout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_name:

//                ToastUtils.getInstance().showTextToast(getActivity(), "sssssssssssssss");
                break;
            case R.id.mine_position:
//                ToastUtils.getInstance().showTextToast(getActivity(), "aaaaaaaaaaaaaaaaa");
                break;
            case R.id.mine_data:

                ToastUtils.getInstance().showTextToast(getActivity(), "个人资料");

                break;
            case R.id.mine_password:
//  ChangePassword
                ChangePasswordActivity.start(getActivity());
//                ToastUtils.getInstance().showTextToast(getActivity(), "修改密码");
                break;
            case R.id.mine_course:

                ToastUtils.getInstance().showTextToast(getActivity(), "我的课程");

                break;
            case R.id.mine_examination:

                ToastUtils.getInstance().showTextToast(getActivity(), "我的考试");
                break;
            case R.id.mine_about:
                AboutUsActivity.start(getActivity());
//                ToastUtils.getInstance().showTextToast(getActivity(), "关于我们");
                break;
            case R.id.mine_cache:

                ToastUtils.getInstance().showTextToast(getActivity(), "清理缓存");
                break;
            case R.id.mine_signout:
//                ToastUtils.getInstance().showTextToast(getActivity(), "退出登陆");

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle("确定退出易科研吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();




                        if (Hawk.contains(Contants.loginInformation)) {
                            Hawk.delete(Contants.loginInformation);

                           //返回首页
                        }

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

                break;
        }
    }

}