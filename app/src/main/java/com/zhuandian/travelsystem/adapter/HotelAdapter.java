package com.zhuandian.travelsystem.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuandian.base.BaseAdapter;
import com.zhuandian.base.BaseViewHolder;
import com.zhuandian.travelsystem.R;
import com.zhuandian.travelsystem.entity.HotelEntity;
import com.zhuandian.travelsystem.entity.SceneryEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * date：2019/4/19
 */
public class HotelAdapter extends BaseAdapter<HotelEntity, BaseViewHolder> {

    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    private List<SceneryEntity> mDatas;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HotelAdapter(List<HotelEntity> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    protected void converData(BaseViewHolder myViewHolder, final HotelEntity hotelEntity, final int position) {
        ButterKnife.bind(this, myViewHolder.itemView);
        tvName.setText(hotelEntity.getName());
        tvContent.setText(hotelEntity.getContent());
        Glide.with(myViewHolder.itemView.getContext()).load(hotelEntity.getImgUrl()).into(ivImg);
        tvPrice.setText(hotelEntity.getPrice() + "/人");
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(hotelEntity);
                }
            }
        });
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_common_layout;
    }

    public interface OnItemClickListener {
        void onClick(HotelEntity hotelEntity);
    }
}
