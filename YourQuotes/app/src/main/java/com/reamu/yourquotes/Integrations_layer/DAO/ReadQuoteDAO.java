package com.reamu.yourquotes.Integrations_layer.DAO;

import android.content.Context;
import android.util.Log;

import com.reamu.yourquotes.models.QuotesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Reena on 09-06-2016.
 */
public class ReadQuoteDAO {

    public static ReadQuoteDAO instance = null;
    public LoadQuotes listener;

    public interface LoadQuotes {
        void getQuotes(ArrayList<QuotesModel> arrQuotes);
    }

    public static ReadQuoteDAO getInstance() {

        if (instance == null) {
            instance = new ReadQuoteDAO();
        }
        return instance;
    }

    public ArrayList<QuotesModel> readJsonFromText(Context context, LoadQuotes quoteListener) {
        listener = quoteListener;
        String text = null;
        ArrayList<QuotesModel> arrQuote = new ArrayList<>();


        try {
            InputStream input = context.getAssets().open("quotes.txt");

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            // byte buffer into a string
            text = new String(buffer);
            Log.e("Quote Model", "CountryCode:" + text);

            try {

                QuotesModel quoteModel;

                JSONArray quoteMainArray = new JSONArray(text);


                for (int i = 0; i < quoteMainArray.length(); i++) {
                    JSONObject jsonQuote = quoteMainArray.getJSONObject(i);
                    quoteModel = new QuotesModel();
                    quoteModel.setAuthorName(jsonQuote.getString("AuthorName"));
                    quoteModel.setQuoteTxt(jsonQuote.getString("Quote"));
                    quoteModel.setSrNo(jsonQuote.getInt("SrNo"));
                    quoteModel.setTopic(jsonQuote.getString("Topic"));

                    arrQuote.add(quoteModel);
                }

                listener.getQuotes(arrQuote);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrQuote;
    }

}
