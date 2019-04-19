package com.zhuandian.travelsystem.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.adapter.HotelAdapter;
import com.zhuandian.travelsystem.adapter.SceneryAdapter;
import com.zhuandian.travelsystem.entity.HotelEntity;
import com.zhuandian.travelsystem.entity.SceneryEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OrderActivity extends BaseActivity {


    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rv_hotel_list)
    RecyclerView rvHotelList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void setUpView() {
        initData();
    }

    private void initData() {
        BmobQuery<SceneryEntity> sceneryEntityBmobQuery = new BmobQuery<>();
        sceneryEntityBmobQuery.addWhereEqualTo("state", 2)
                .findObjects(new FindListener<SceneryEntity>() {
                    @Override
                    public void done(List<SceneryEntity> list, BmobException e) {
                        if (e == null) {

                            SceneryAdapter adapter = new SceneryAdapter(list, OrderActivity.this);
                            rvList.setAdapter(adapter);
                            rvList.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                            adapter.setOnItemClickListener(new SceneryAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(SceneryEntity sceneryEntity) {
                                    Intent intent = new Intent(OrderActivity.this, DetailActivity.class);
                                    intent.putExtra("type", 1);
                                    intent.putExtra("entity", sceneryEntity);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });


        BmobQuery<HotelEntity> hotelEntityBmobQuery = new BmobQuery<>();
        hotelEntityBmobQuery.addWhereEqualTo("state", 2)
                .findObjects(new FindListener<HotelEntity>() {
                    @Override
                    public void done(List<HotelEntity> list, BmobException e) {
                        if (e == null) {
                            HotelAdapter adapter = new HotelAdapter(list, OrderActivity.this);
                            rvHotelList.setAdapter(adapter);
                            rvHotelList.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                            adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(HotelEntity hotelEntity) {
                                    Intent intent = new Intent(OrderActivity.this, DetailActivity.class);
                                    intent.putExtra("type", 2);
                                    intent.putExtra("entity", hotelEntity);
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                });
    }


}
