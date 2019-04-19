package com.zhuandian.travelsystem.business.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuandian.base.BaseFragment;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.adapter.HotelAdapter;
import com.zhuandian.travelsystem.business.DetailActivity;
import com.zhuandian.travelsystem.entity.HotelEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * desc :
 * author：xiedong
 * date：2019/4/19
 */
public class HotelFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        BmobQuery<HotelEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<HotelEntity>() {
            @Override
            public void done(List<HotelEntity> list, BmobException e) {
                if (e == null) {
                    final HotelAdapter adapter = new HotelAdapter(list, actitity);
                    rvList.setAdapter(adapter);
                    rvList.setLayoutManager(new LinearLayoutManager(actitity));
                    adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(HotelEntity hotelEntity) {
                          Intent intent =  new Intent(actitity,DetailActivity.class);
                          intent.putExtra("type",2);
                          intent.putExtra("entity",hotelEntity);
                          startActivity(intent);
                        }
                    });
                }
            }
        });
    }


}
