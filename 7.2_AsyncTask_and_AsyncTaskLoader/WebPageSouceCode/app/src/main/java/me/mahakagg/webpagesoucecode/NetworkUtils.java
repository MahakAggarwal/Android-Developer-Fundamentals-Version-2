package me.mahakagg.webpagesoucecode;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    // network class
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    static String getSourceCode(Context context, String queryString, String transferProtocol){
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String htmlSourceCode = null;
        String[] protocol = context.getResources().getStringArray(R.array.http_array);
        try{
            Uri builder;
            if (transferProtocol.equals(protocol[0])){
                // http
                builder = Uri.parse(queryString).buildUpon()
                        .scheme(HTTP)
                        .build();
            }
            else{
                // https
                builder = Uri.parse(queryString).buildUpon()
                        .scheme(HTTPS)
                        .build();
            }

            URL requestURL = new URL(builder.toString());
            // httpURLConnection automatically goes for http or https based on the URI scheme
            httpURLConnection = (HttpURLConnection) requestURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            if (stringBuilder.length() == 0){
                return null;
            }
            htmlSourceCode = stringBuilder.toString();

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, htmlSourceCode);
        return htmlSourceCode;
    }
}
