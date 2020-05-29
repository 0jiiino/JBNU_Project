package com.jbnu.project1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookList extends AppCompatActivity {

    private static String TAG = "project1_BookList";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_CATEGORY = "bkcategory";
    private static final String TAG_NAME = "bkname";
    private static final String TAG_WRITER ="bkwriter";
    private static final String TAG_COMPANY ="bkcompany";


    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        mlistView = (ListView) findViewById(R.id.listView_main_list);
        mArrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://jimam.dothome.co.kr/getjson.php");
    }


    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(BookList.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

                mJsonString = result;
                showResult();

        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(8000);
                httpURLConnection.setConnectTimeout(8000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
                bufferedReader.close();
                return sb.toString().trim();
            } catch (Exception e) {
                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();
                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String category = item.getString(TAG_CATEGORY);
                String name = item.getString(TAG_NAME);
                String writer = item.getString(TAG_WRITER);
                String company = item.getString(TAG_COMPANY);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_CATEGORY, category);
                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_WRITER, writer);
                hashMap.put(TAG_COMPANY, company);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    BookList.this, mArrayList, R.layout.view_book,
                    new String[]{TAG_CATEGORY,TAG_NAME, TAG_WRITER,TAG_COMPANY},
                    new int[]{R.id.textView1, R.id.textView2, R.id.textView3,R.id.textView4}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);

        }

    }
}
