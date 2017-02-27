package com.dev7.oldster.adsnativerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.NativeExpressAdView;

import java.util.List;

/**
 * Created by Old'ster on 2/27/2017.
 */

public class AdsNativeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COMMENT_ITEM_VIEW_TYPE = 0;
    private static final int ADS_NATIVE_ITEM_VIEW_TYPE = 1;

    private Context mContext;
    private List<Object> mRecyclerViewItem;

    public AdsNativeAdapter(Context mContext, List<Object> mRecyclerViewItem) {
        this.mContext = mContext;
        this.mRecyclerViewItem = mRecyclerViewItem;
    }

    public class CommentItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewName;
        private TextView mTextViewDate;
        private TextView mTextViewContent;

        public CommentItemViewHolder(View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.tvName);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tvDate);
            mTextViewContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }

    public class NativeExpressAdViewHolder extends RecyclerView.ViewHolder {

        public NativeExpressAdViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ADS_NATIVE_ITEM_VIEW_TYPE:
                View nativeExpressLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.native_express_ad_container, parent, false);
                return new NativeExpressAdViewHolder(nativeExpressLayoutView);

            case COMMENT_ITEM_VIEW_TYPE:
            default:
                View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_view_content, parent, false);
                return new CommentItemViewHolder(menuItemLayoutView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ADS_NATIVE_ITEM_VIEW_TYPE:
                NativeExpressAdViewHolder nativeExpressAdHolder = (NativeExpressAdViewHolder) holder;
                NativeExpressAdView adView = (NativeExpressAdView) mRecyclerViewItem.get(position);

                ViewGroup adCardView = (ViewGroup) nativeExpressAdHolder.itemView;
                adCardView.removeAllViews();

                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adCardView.addView(adView);
                break;
            case COMMENT_ITEM_VIEW_TYPE:
            default:
                CommentItemViewHolder commentItemHolder = (CommentItemViewHolder) holder;
                CommentData data = (CommentData) mRecyclerViewItem.get(position);
                commentItemHolder.mTextViewName.setText(data.getName());
                commentItemHolder.mTextViewDate.setText(data.getDate());
                commentItemHolder.mTextViewContent.setText(data.getConntent());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return COMMENT_ITEM_VIEW_TYPE;
        return (position % MainActivity.ITEMS_PER_AD == 0) ? ADS_NATIVE_ITEM_VIEW_TYPE : COMMENT_ITEM_VIEW_TYPE;
       // return COMMENT_ITEM_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItem.size();
    }


}
