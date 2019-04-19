package com.zhuandian.travelsystem.business.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuandian.base.BaseFragment;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.adapter.HotelAdapter;
import com.zhuandian.travelsystem.adapter.SceneryAdapter;
import com.zhuandian.travelsystem.business.DetailActivity;
import com.zhuandian.travelsystem.entity.HotelEntity;
import com.zhuandian.travelsystem.entity.SceneryEntity;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * desc :
 * author：xiedong
 * date：2019/4/19
 */
public class SceneryFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        BmobQuery<SceneryEntity> query = new BmobQuery<>();
        query.findObjects(new FindListener<SceneryEntity>() {
            @Override
            public void done(List<SceneryEntity> list, BmobException e) {
                if (e == null) {
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
    }
}
