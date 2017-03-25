package com.infomation.haiffeng.App.infomation.Geek;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.App.clickinterface.OnItemClickListener;
import com.infomation.haiffeng.enity.InformationBean;
import com.zly.www.easyrecyclerview.adapter.CommonAdapter;
import com.zly.www.easyrecyclerview.adapter.viewholder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 黄海峰 on 2017/3/18.
 */

public class InformationDealAdapter extends CommonAdapter<InformationBean,InformationDealAdapter.viewHolder> {

    private OnItemClickListener mOnItemClickListener;

    class viewHolder extends BaseViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public viewHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        viewHolder holder = new viewHolder(inflateView(R.layout.item_information_geek, parent));
        return holder;
    }

    @Override
    public void bindCustomViewHolder(viewHolder holder, InformationBean informationBeen, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.OnItemClick(v,position);
                }
            }
        });
        holder.tvContent.setText(informationBeen.getDesc());
        holder.tvTitle.setText(informationBeen.getTitle());
        holder.tvTime.setText(informationBeen.getSubdesc());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

}
