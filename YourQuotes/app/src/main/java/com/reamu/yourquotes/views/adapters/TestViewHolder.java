package com.reamu.yourquotes.views.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.florent37.materialleanback.MaterialLeanBack;
import com.reamu.yourquotes.R;
import com.reamu.yourquotes.views.custom_control.CustomTextView;

/**
 * Created by Reena on 16-06-2016.
 */
public class TestViewHolder extends MaterialLeanBack.ViewHolder {
    public CustomTextView tvAuthor;
    public CustomTextView tvQuote;
    public CustomTextView tvTopic;
    public ImageButton btnShare;
    public ImageButton btnFav;

    public TestViewHolder(View itemView) {
        super(itemView);
        this.tvAuthor = (CustomTextView) itemView.findViewById(R.id.tvAuthorr);
        this.tvQuote = (CustomTextView) itemView.findViewById(R.id.tvQuotes);
        this.btnShare = (ImageButton) itemView.findViewById(R.id.imgBtnShare);
        this.btnFav = (ImageButton) itemView.findViewById(R.id.imgBtnFav);
        this.tvTopic = (CustomTextView) itemView.findViewById(R.id.tvTopic);
    }

}
