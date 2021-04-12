package com.example.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FechBook extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    FechBook (TextView titleText, TextView authorText){
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i  = 0;
            String title = null;
            String authors = null;

            while (i < itemsArray.length() && (authors == null && title == null)){
                //get current info
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                //try to get the author and tittle from current item, catch if field id empty
                // The loop ends at the first match in the response. More responses might be available, but this app only displays the first one
                try{
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                }catch (Exception e){
                    mTitleText.get().setText(R.string.nenalezeno);
                    mAuthorText.get().setText("");
                    e.printStackTrace();
                }
                i++;

                if(title != null && authors != null){
                    //Because the references to the TextView objects are WeakReference objects, you have to dereference them using the get() method.
                    mTitleText.get().setText(title);
                    mAuthorText.get().setText(authors);
                } else{
                    mTitleText.get().setText(R.string.nenalezeno);
                    mAuthorText.get().setText("");
                }


            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
