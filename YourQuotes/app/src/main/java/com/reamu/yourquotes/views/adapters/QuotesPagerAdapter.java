package com.reamu.yourquotes.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.reamu.yourquotes.R;
import com.reamu.yourquotes.helpers.CommonUtility;
import com.reamu.yourquotes.models.QuotesModel;

import java.util.ArrayList;

/**
 * Created by Reena on 13-06-2016.
 */
public class QuotesPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    private ArrayList<QuotesModel> mArrQuoteList;

    TextView tvAuthor;
    TextView tvQuote;
    TextView tvTopic;
    ImageButton btnShare;
    ImageButton btnFav;


    // Constructor
    public QuotesPagerAdapter(ArrayList<QuotesModel> arrQuoteList, Context context) {
        this.mArrQuoteList = arrQuoteList;
        this.mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mArrQuoteList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof View) {
            return Integer.valueOf(((View) object).getTag().toString());
        }
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        initViews(itemView, position);
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object != null && object instanceof View) {
            container.removeView((View) object);
        }
    }

    public void initViews(View itemView, final int position) {
        this.tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthorr);
        this.tvQuote = (TextView) itemView.findViewById(R.id.tvQuotes);
        this.btnShare = (ImageButton) itemView.findViewById(R.id.imgBtnShare);
        this.btnFav = (ImageButton) itemView.findViewById(R.id.imgBtnFav);
        this.tvTopic = (TextView) itemView.findViewById(R.id.tvTopic);

        tvQuote.setText(mArrQuoteList.get(position).getQuoteTxt());
        tvAuthor.setText(mArrQuoteList.get(position).getAuthorName());
        tvTopic.setText(mArrQuoteList.get(position).getTopic());

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtility.sharedInstance().shareOnSocial(mContext, mArrQuoteList.get(position).getQuoteTxt());
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}

