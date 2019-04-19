package com.zhuandian.travelsystem.business;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhuandian.base.BaseActivity;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.entity.HotelEntity;
import com.zhuandian.travelsystem.entity.SceneryEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class DetailActivity extends BaseActivity {


    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    private SceneryEntity sceneryEntity;
    private HotelEntity hotelEntity;
    private int type;
    private String shareContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void setUpView() {
        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            sceneryEntity = ((SceneryEntity) getIntent().getSerializableExtra("entity"));
            tvPrice.setText(sceneryEntity.getPrice() + "/人");
            shareContent = sceneryEntity.getName() + "\n" + sceneryEntity.getContent();
            tvDesc.setText(shareContent);
            Glide.with(DetailActivity.this).load(sceneryEntity.getImgUrl()).into(ivImg);
            BmobQuery<SceneryEntity> query = new BmobQuery<>();
            query.addWhereEqualTo("objectId", sceneryEntity.getObjectId())
                    .findObjects(new FindListener<SceneryEntity>() {
                        @Override
                        public void done(final List<SceneryEntity> list, BmobException e) {
                            if (e == null) {
                                final int state = list.get(0).getState();

                                tvSubmit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (state == 1) {
                                            SceneryEntity sceneryEntity = list.get(0);
                                            sceneryEntity.setState(2);
                                            sceneryEntity.update(new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if (e == null) {
                                                        Toast.makeText(DetailActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                                                        tvSubmit.setText("提交预约");
                                                    }
                                                }
                                            });
                                        } else {
                                            SceneryEntity sceneryEntity = list.get(0);
                                            sceneryEntity.setState(1);
                                            sceneryEntity.update(new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if (e == null) {
                                                        Toast.makeText(DetailActivity.this, "预定成功", Toast.LENGTH_SHORT).show();
                                                        tvSubmit.setText("取消预约");
                                                    }
                                                }
                                            });
                                        }
                                        setUpView();
                                    }
                                });
                            }
                        }
                    });


        } else if (type == 2) {
            hotelEntity = (HotelEntity) getIntent().getSerializableExtra("entity");
            tvPrice.setText(hotelEntity.getPrice() + "/人");
            shareContent = hotelEntity.getName() + "\n" + hotelEntity.getContent();
            tvDesc.setText(shareContent);
            Glide.with(DetailActivity.this).load(hotelEntity.getImgUrl()).into(ivImg);
            BmobQuery<HotelEntity> query = new BmobQuery<>();
            query.addWhereEqualTo("objectId", hotelEntity.getObjectId())
                    .findObjects(new FindListener<HotelEntity>() {
                        @Override
                        public void done(final List<HotelEntity> list, BmobException e) {
                            if (e == null) {
                                final int state = list.get(0).getState();

                                tvSubmit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (state == 1) {
                                            HotelEntity hotelEntity = list.get(0);
                                            hotelEntity.setState(2);
                                            hotelEntity.update(new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if (e == null) {
                                                        Toast.makeText(DetailActivity.this, "取消成功", Toast.LENGTH_SHORT).show();
                                                        tvSubmit.setText("提交预约");
                                                    }
                                                }
                                            });
                                        } else {
                                            HotelEntity hotelEntity = list.get(0);
                                            hotelEntity.setState(1);
                                            hotelEntity.update(new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if (e == null) {
                                                        Toast.makeText(DetailActivity.this, "预定成功", Toast.LENGTH_SHORT).show();
                                                        tvSubmit.setText("取消预约");
                                                    }
                                                }
                                            });
                                        }

                                        setUpView();
                                    }
                                });
                            }
                        }
                    });
        }


    }


    @OnClick({R.id.iv_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
                startActivity(Intent.createChooser(textIntent, "分享"));
                break;

        }
    }


}
