package com.reamu.yourquotes.FrontEnd;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.reamu.yourquotes.Controller.MyApplication;
import com.reamu.yourquotes.R;
import com.reamu.yourquotes.views.adapters.QuotesPagerAdapter;

/**
 * Created by Reena on 13-06-2016.
 */
public class DetailQuotesActivity extends AppCompatActivity {

    ViewPager mQuotesPager;
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_detailsquotes);
        initViews();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initViews() {
        mQuotesPager = (ViewPager) findViewById(R.id.quotesPager);
        mQuotesPager.setAdapter(new QuotesPagerAdapter(MyApplication.getmArrListQuotes(), mContext));
    }
}
