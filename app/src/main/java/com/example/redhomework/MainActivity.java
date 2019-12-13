package com.example.redhomework;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Item> mlist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sendHttpRequest("http://gank.io/api/data/Android/10/1");
        sendHttpRequest("http://gank.io/api/data/Android/10/2");
        sendHttpRequest("http://gank.io/api/data/Android/10/3");
    }

    private void parseJSONwithJSONObject(final String jsondata) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject object = new JSONObject(jsondata);
                    JSONArray jsonArray = new JSONArray(object.getString("results"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object1 = jsonArray.getJSONObject(i);
                        String[] result = new String[3];
                        result[0] = object1.getString("desc");
                        result[1] = object1.getString("who");
                        result[2] = object1.getString("publishedAt");
                        result[2] = result[2].substring(0,9);
                        Item item = new Item(result[2], result[1], result[0]);
                            showResponse(item);
                    }
                } catch (JSONException e) {
                }
            }
        }).start();
    }
    private void showResponse(final Item item) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mlist.add(item);
                RecyclerAdapter adapter = new RecyclerAdapter(mlist);
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });
    }
    public void sendHttpRequest(final String address) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);

                    }
                    parseJSONwithJSONObject(response.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}