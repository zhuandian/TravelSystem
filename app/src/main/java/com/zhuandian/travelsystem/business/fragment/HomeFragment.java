package com.zhuandian.travelsystem.business.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhuandian.base.BaseFragment;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.adapter.HotelAdapter;
import com.zhuandian.travelsystem.adapter.SceneryAdapter;
import com.zhuandian.travelsystem.business.DetailActivity;
import com.zhuandian.travelsystem.entity.HotelEntity;
import com.zhuandian.travelsystem.entity.SceneryEntity;
import com.zhuandian.travelsystem.util.GlideImageLoader;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * desc :
 * author：xiedong
 * date：2019/4/19
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rv_hotel_list)
    RecyclerView rvHotelList;
    List<String> mDatas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        initData();
    }

    private void initData() {

        Observable.just(mDatas)
                .map(new Function<List<String>, List<String>>() {
                    @Override
                    public List<String> apply(final List<String> objects) throws Exception {
                        BmobQuery<SceneryEntity> sceneryEntityBmobQuery = new BmobQuery<>();
                        sceneryEntityBmobQuery.setLimit(2)
                                .findObjects(new FindListener<SceneryEntity>() {
                                    @Override
                                    public void done(List<SceneryEntity> list, BmobException e) {
                                        if (e == null) {
                                            mDatas.add(list.get(0).getImgUrl());
                                            SceneryAdapter adapter = new SceneryAdapter(list, actitity);
                                            rvList.setAdapter(adapter);
                                            rvList.setLayoutManager(new LinearLayoutManager(actitity));
                                            adapter.setOnItemClickListener(new SceneryAdapter.OnItemClickListener() {
                                                @Override
                                                public void onClick(SceneryEntity sceneryEntity) {
                                                    Intent intent =  new Intent(actitity,DetailActivity.class);
                                                    intent.putExtra("type",1);
                                                    intent.putExtra("entity",sceneryEntity);
                                                    startActivity(intent);
                                                }
                                            });
                                        }
                                    }
                                });
                        return objects;
                    }
                }).map(new Function<List<String>, List<String>>() {
            @Override
            public List<String> apply(final List<String> objects) throws Exception {
                BmobQuery<HotelEntity> sceneryEntityBmobQuery = new BmobQuery<>();
                sceneryEntityBmobQuery.setLimit(3)
                        .findObjects(new FindListener<HotelEntity>() {
                            @Override
                            public void done(List<HotelEntity> list, BmobException e) {
                                if (e == null) {
                                    mDatas.add(list.get(0).getImgUrl());
                                    HotelAdapter adapter = new HotelAdapter(list, actitity);
                                    rvHotelList.setAdapter(adapter);
                                    rvHotelList.setLayoutManager(new LinearLayoutManager(actitity));
                                    initBanner(mDatas);
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
                return objects;
            }
        }).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> objects) {
//                initBanner(mDatas);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void initBanner(List<String> objects) {

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(objects);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


}
