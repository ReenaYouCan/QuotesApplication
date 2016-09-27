package com.reamu.yourquotes.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialleanback.MaterialLeanBack;
import com.reamu.yourquotes.FrontEnd.ScrollingActivity;
import com.reamu.yourquotes.R;
import com.reamu.yourquotes.helpers.CommonUtility;
import com.reamu.yourquotes.models.QuotesModel;

import java.util.ArrayList;

/**
 * Created by Reena on 20-06-2016.
 */
public class MaterialBeanAdapter extends MaterialLeanBack.Adapter<TestViewHolder> {
    private ArrayList<QuotesModel> mQuoteList;
    private Context mContext;

    public MaterialBeanAdapter(Context context, ArrayList<QuotesModel> list) {
        mContext = context;
        mQuoteList = list;
    }

    @Override
    public int getLineCount() {
        return 5;
    }


    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int row) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_item, viewGroup, false);


        TestViewHolder myViewHolder = new TestViewHolder(view);
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, final int position) {

        holder.tvQuote.setText(mQuoteList.get(position).getQuoteTxt());
        holder.tvAuthor.setText(mQuoteList.get(position).getAuthorName());
        holder.tvTopic.setText(mQuoteList.get(position).getTopic());

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtility.sharedInstance().shareOnSocial(mContext, mQuoteList.get(position).getQuoteTxt());
            }
        });
        holder.btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tvQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ScrollingActivity.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getCellsCount(int line) {
        return mQuoteList.size();
    }

    @Override
    public String getTitleForRow(int row) {
        return "Line " + row;
    }

//    @Override
//    public boolean isCustomView(int row) {
//        return row == 3;
//    }
//
//    @Override
//    public void onBindCustomView(RecyclerView.ViewHolder viewHolder, int row) {
//        super.onBindCustomView(viewHolder, row);
//    }
}
