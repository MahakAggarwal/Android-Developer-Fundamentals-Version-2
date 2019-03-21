package me.mahakagg.webpagesoucecode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
* Answers to homework questions
*
* Q1 - What permissions does your app need to connect to the internet?
* A1 - android.permission.INTERNET
*
* Q2 - How does your app check that internet connectivity is available?
* A2 - In the manifest: request ACCESS_NETWORK_STATE permission
*  In the code: Use ConnectivityManager to check for an active network before connecting to the network.
*
* Q3 - Where do you implement the loader callback method that's triggered when the loader finishes executing its task?
* A3 - n the Activity that displays the results of the task. The Activity must implement LoaderManager.LoaderCallbacks.
*
* Q4 - When the user rotates the device, how do AsyncTask and AsyncTaskLoader behave differently if they are in the process of running a task in the background?
* A4 - A running AsyncTask becomes disconnected from the activity and stops running, preserving system resources. A running AsyncTaskLoader automatically restarts execution of its task from the beginning. The activity displays the results.
*
* Q5 - How do you initialize an AsyncTaskLoader to perform steps, such as initializing variables, that must be done before the loader starts performing its background task?
* A5 - In onCreateLoader() in the activity, create an instance of the AsyncTaskLoader subclass. In the loader's constructor, perform initialization tasks.
*
* Q6 - What methods must an AsyncTaskLoader implement?
* A6 - loadInBackground() and onStartLoading; the subclass implementing AsyncTaskLoader will also need a constructor
*/


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, AdapterView.OnItemSelectedListener {

    private String mSpinnerValue;
    private EditText mURLEditText;
    private TextView mSourceCodeTextView;
    private static final String QUERY = "queryString";
    private static final String PROTOCOL = "transferProtocol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mURLEditText = findViewById(R.id.url_EditText);
        mSourceCodeTextView = findViewById(R.id.page_source_code);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.http_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.http_spinner);
        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }

        if (getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        // TODO: finish work here
        String queryString = "";
        String transferProtocol = "";
        if (bundle != null){
            queryString = bundle.getString(QUERY);
            transferProtocol = bundle.getString(PROTOCOL);
        }
        return new SourceCodeLoader(this, queryString, transferProtocol);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try{
            mSourceCodeTextView.setText(s);
        }
        catch (Exception e){
            e.printStackTrace();
            mSourceCodeTextView.setText(R.string.no_response);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // empty method
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerValue = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String[] values = getResources().getStringArray(R.array.http_array);
        mSpinnerValue = values[0];
    }

    public void getSourceCode(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        String queryString = mURLEditText.getText().toString();


        // check connectivity before executing query
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null){
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected() && (queryString.length() != 0)){
            Bundle queryBundle = new Bundle();
            queryBundle.putString(QUERY, queryString);
            queryBundle.putString(PROTOCOL, mSpinnerValue);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            mSourceCodeTextView.setText(R.string.loading);
        }
        else{
            if (queryString.length() == 0){
                Toast.makeText(this, R.string.no_url, Toast.LENGTH_LONG).show();
            }
            else if(!URLUtil.isValidUrl(queryString)){
                Toast.makeText(this, R.string.invalid_url, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
            }
        }

    }

}
