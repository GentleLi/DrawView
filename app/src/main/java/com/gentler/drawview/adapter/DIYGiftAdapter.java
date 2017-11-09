package com.gentler.drawview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gentler.drawview.R;
import com.gentler.drawview.model.DIYGiftModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/11/9.
 */

public class DIYGiftAdapter extends BaseRecyclerAdapter<DIYGiftModel,DIYGiftAdapter.DIYGiftViewHolder>{

    public DIYGiftAdapter(LayoutInflater mInflater) {
        super(mInflater);
    }

    @Override
    public DIYGiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DIYGiftViewHolder(mInflater.inflate(R.layout.rv_gift_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final DIYGiftViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        DIYGiftModel model=datas.get(position);
        holder.mImgGift.setImageResource(model.getGiftRes());
        holder.mLayRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnGiftItemClickListener){
                    mOnGiftItemClickListener.onGiftItemClick(holder,position);
                }
            }
        });
    }

    private OnGiftItemClickListener mOnGiftItemClickListener;
    public void setOnGiftItemClickListener(OnGiftItemClickListener listener){
        this.mOnGiftItemClickListener=listener;
    }

    public interface OnGiftItemClickListener{
        void onGiftItemClick(DIYGiftViewHolder holder,int position);
    }

    public static class DIYGiftViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_gift)
        ImageView mImgGift;
        @BindView(R.id.lay_root)
        RelativeLayout mLayRoot;

        public DIYGiftViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
