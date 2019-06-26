package com.christopherbare.newdemothingnews;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.transform.Source;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    Source source;
    Activity activity;
    ArrayList<Source> sources;
    SourceAdapter adapter;
    RecyclerView sourceList;
    String newsAPI = "https://newsapi.org/v2/sources?apiKey=5e9b2a45503c43e988151236956f3f54";
    String apiFirst = "https://newsapi.org/v2/top-headlines?apiKey=5e9b2a45503c43e988151236956f3f54&sources=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private class GetSourcesAsync extends AsyncTask<String, Void, ArrayList<Source>> {
        ProgressDialog dialog;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected ArrayList<Source> doInBackground(String... params) {
            HttpURLConnection connection = null;
            ArrayList<Source> result = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    //The JSON file in string format
                    String json = IOUtils.toString(connection.getInputStream(), "UTF-8");

                    //The entire JSON object
                    JSONObject root = new JSONObject(json);

                    //The array within the JSON object
                    JSONArray sourceJSON = root.getJSONArray("sources");


                    for (int i = 0; i < sourceJSON.length(); i++) {

                        //Initialize objects
                        source = new Source();

                        //Get the JSON sources
                        JSONObject sources = sourceJSON.getJSONObject(i);

                        //Set the fields for object from the JSON one
                        source.setName(sources.getString("name"));
                        source.setId(sources.getString("id"));

                        Log.d("demo", "doInBackground: " + source.toString());

                        result.add(source);
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Getting Sources");
            dialog.show();
        }


        public GetSourcesAsync() {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPostExecute(ArrayList<Source> result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (sources.size() == 0) sources.addAll(result);


            adapter = new SourceAdapter(getApplicationContext(), R.layout.source_item, result);
            sourceList = findViewById(R.id.recyclerView);
            sourceList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

}
