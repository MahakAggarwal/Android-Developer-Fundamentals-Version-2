package me.mahakagg.whowroteitloadercc;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";
    private static final String DOWNLOADABLE = "download";

    static String getBookInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String bookJSONString = null;

        try {
            // Uri is used for the Books API - all results have the same base URI and then rest changes due to the search
            Uri builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .appendQueryParameter(DOWNLOADABLE, "epub")
                    .build();
            // convert URI to URL for the www
            URL requestURL = new URL(builtUri.toString());

            // use HttpURLConnection to connect to the internet
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // get the input from the url connection
            InputStream inputStream = urlConnection.getInputStream();
            // buffered reader for the input stream
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // string builder to hold the JSON response
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
                // not necessary for JSON response but is good for debugging
                stringBuilder.append("\n");
            }
            if (stringBuilder.length() == 0){
                return null;
            }
            bookJSONString = stringBuilder.toString();

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, bookJSONString);
        return bookJSONString;
    }
}
