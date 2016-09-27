package com.reamu.yourquotes.FrontEnd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialleanback.MaterialLeanBack;
import com.reamu.yourquotes.Controller.MyApplication;
import com.reamu.yourquotes.R;
import com.reamu.yourquotes.helpers.QuotesConstants;
import com.reamu.yourquotes.models.QuotesModel;
import com.reamu.yourquotes.views.adapters.MaterialBeanAdapter;
import com.reamu.yourquotes.views.adapters.TestViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private Context mContext;
    MaterialLeanBack mLeanBack;
    private String mAutherName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_scrolling);
        getDataFromIntent();
        initToolbar();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initViews();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("" + mAutherName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getDataFromIntent() {
        Intent intent = getIntent();
        mAutherName = intent.getStringExtra(QuotesConstants.KEY_AUTHER_NAME);
    }


    public void initViews() {

        mLeanBack = (MaterialLeanBack) findViewById(R.id.materialLeanBack);
        final ArrayList<QuotesModel> arrayList = MyApplication.getmArrListQuotes();

        final ArrayList<QuotesModel> mArrAuthorList = new ArrayList<>();


        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getAuthorName().equalsIgnoreCase(mAutherName)) {
                mArrAuthorList.add(arrayList.get(i));
                Log.e(getClass().getSimpleName(), "" + arrayList.get(i).getAuthorName());
            }
        }

        Log.e(getClass().getSimpleName(), "mArrAuthorList" + mArrAuthorList.size());
        mLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
            @Override
            public int getLineCount() {
                return 3;
            }

            @Override
            public int getCellsCount(int line) {
                return 5;
            }

            @Override
            public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);
                return new TestViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TestViewHolder viewHolder, int i) {
                viewHolder.tvQuote.setText(mArrAuthorList.get(i).getQuoteTxt());
                viewHolder.tvAuthor.setText(mArrAuthorList.get(i).getAuthorName());
                viewHolder.tvTopic.setText(mArrAuthorList.get(i).getTopic());
            }

            @Override
            public String getTitleForRow(int row) {
//                return "Line " + row;
                return "";
            }

            //region customView
//            @Override
//            public RecyclerView.ViewHolder getCustomViewForRow(ViewGroup viewgroup, int row) {
//                if (row == 3) {
//                    View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.header, viewgroup, false);
//                    return new RecyclerView.ViewHolder(view) {
//                    };
//                } else
//                    return null;
//            }

//            @Override
//            public boolean isCustomView(int row) {
//                return row == 3;
//            }

//            @Override
//            public void onBindCustomView(RecyclerView.ViewHolder viewHolder, int row) {
//                super.onBindCustomView(viewHolder, row);
//            }

            //endregion

        });


    }
}
