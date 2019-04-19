package com.zhuandian.travelsystem.business.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuandian.base.BaseFragment;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.business.HelpActivity;
import com.zhuandian.travelsystem.business.OrderActivity;
import com.zhuandian.travelsystem.business.UserInfoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * desc :
 * author：xiedong
 * date：2019/4/19
 */
public class MyFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.tv_user_info, R.id.tv_my_order, R.id.tv_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_info:
                startActivity(new Intent(actitity,UserInfoActivity.class));
                break;
            case R.id.tv_my_order:
                startActivity(new Intent(actitity,OrderActivity.class));
                break;
            case R.id.tv_help:
                startActivity(new Intent(actitity,HelpActivity.class));
                break;
        }
    }
}
