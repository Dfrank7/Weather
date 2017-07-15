package com.example.dfrank.weather;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask downloadTask = new DownloadTask();

        downloadTask.execute("http://api.openweathermap.org/data/2.5/weather?q=London,uk");


    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url;
            HttpsURLConnection connection = null;
            try {
                url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char currentChar = (char) data;
                    result += currentChar;
                    reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "failed";

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("weather", result);
        }
    }

}
