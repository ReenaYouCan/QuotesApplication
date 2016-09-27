package com.reamu.yourquotes.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.reamu.yourquotes.Controller.MainLeanBackActivity;
import com.reamu.yourquotes.FrontEnd.DetailQuotesActivity;
import com.reamu.yourquotes.FrontEnd.ScrollingActivity;
import com.reamu.yourquotes.Integrations_layer.database.QuotesDatabase;
import com.reamu.yourquotes.R;
import com.reamu.yourquotes.helpers.CommonUtility;
import com.reamu.yourquotes.helpers.QuotesConstants;
import com.reamu.yourquotes.models.QuotesModel;
import com.reamu.yourquotes.views.custom_control.CustomTextView;

import java.util.ArrayList;

/**
 * Created by Reena on 10-06-2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<QuotesModel> mArrQuoteList;
    private Context mContext;

    /*Start Overriden methods*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_item, parent, false);


            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;


        }

    }

    /**
     * Set Data to view
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvQuote.setText(mArrQuoteList.get(position).getQuoteTxt());
        holder.tvAuthor.setText(mArrQuoteList.get(position).getAuthorName());
        holder.tvTopic.setText(mArrQuoteList.get(position).getTopic());

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtility.sharedInstance().shareOnSocial(mContext, mArrQuoteList.get(position).getQuoteTxt());
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
                i.putExtra(QuotesConstants.KEY_AUTHER_NAME, mArrQuoteList.get(position).getAuthorName());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrQuoteList.size();
    }


    /*End Overriden methods*/
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CustomTextView tvAuthor;
        CustomTextView tvQuote;
        CustomTextView tvTopic;
        ImageButton btnShare;
        ImageButton btnFav;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvAuthor = (CustomTextView) itemView.findViewById(R.id.tvAuthorr);
            this.tvQuote = (CustomTextView) itemView.findViewById(R.id.tvQuotes);
            this.btnShare = (ImageButton) itemView.findViewById(R.id.imgBtnShare);
            this.btnFav = (ImageButton) itemView.findViewById(R.id.imgBtnFav);
            this.tvTopic = (CustomTextView) itemView.findViewById(R.id.tvTopic);
        }
    }

    // Constructor
    public RecyclerAdapter(ArrayList<QuotesModel> arrQuoteList, Context context) {
        this.mArrQuoteList = arrQuoteList;
        this.mContext = context;
    }


}
