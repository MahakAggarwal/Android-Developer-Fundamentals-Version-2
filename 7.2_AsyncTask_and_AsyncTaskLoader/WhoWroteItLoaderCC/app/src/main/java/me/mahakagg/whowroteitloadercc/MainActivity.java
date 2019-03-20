package me.mahakagg.whowroteitloadercc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
        if (getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchBooks(View view) {
        // hide keyboard when button is clicked
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        String queryString = mBookInput.getText().toString();

        // check connectivity before executing query
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null){
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && (queryString.length() != 0)){
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        }
        else{
            if (queryString.length() == 0){
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            }
            else{
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }

    // this method is called when loader is instantiated
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";

        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }

        return new BookLoader(this, queryString);
    }

    // called when loader finishes; update UI with loader results here
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String title = null;
            String authors = null;
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                    authors = authors.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"", "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }

            if (title != null && authors != null) {
                mTitleText.setText(title);
                mAuthorText.setText(authors);
            } else {
                mTitleText.setText(R.string.no_response);
                mAuthorText.setText("");
            }

        } catch (Exception e) {
            mTitleText.setText(R.string.no_response);
            mAuthorText.setText("");
            e.printStackTrace();
        }
    }

    //cleans up any remaining resources
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // empty method
    }
}
