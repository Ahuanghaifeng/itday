package com.infomation.haiffeng.App.infomation.Gank;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.App.clickinterface.OnItemClickListener;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.squareup.picasso.Picasso;
import com.zly.www.easyrecyclerview.adapter.CommonAdapter;
import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 黄海峰 on 2017/3/18.
 */

public class GInformationDealAdapter extends CommonAdapter<GankInformationBean, GInformationDealAdapter.viewHolder> {

    private OnItemClickListener mOnItemClickListener;

    class viewHolder extends BaseViewHolder {

        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_content)
        ImageView ivContent;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public viewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        viewHolder holder = new viewHolder(inflateView(R.layout.item_information_gank, parent));
        return holder;
    }

    @Override
    public void bindCustomViewHolder(viewHolder holder, GankInformationBean informationBeen, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClick(v, position);
                }
            }
        });
        holder.tvContent.setText(informationBeen.getDesc());
        if (informationBeen.getImages()!=null){
            Picasso.with(getContext()).load(informationBeen.getImages().get(0)).into(holder.ivContent);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
