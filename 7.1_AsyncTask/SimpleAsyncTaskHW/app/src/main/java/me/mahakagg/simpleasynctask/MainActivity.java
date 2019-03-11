package me.mahakagg.simpleasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
* Answers to homework questions
*
* Q1 part 1 - For a ProgressBar, how do you determine the range of values that a ProgressBar can show?
* A1 part 1 - set integer values for android:min and android:max attributes
*
* Q1 part 2 - For a ProgressBar, how do you change how much of the progress bar is filled in?
* A1 part 2 - set integer value for android:progress attribute
*
* Q2 - f an AsyncTask is defined as follows: private class DownloadFilesTask extends AsyncTask<URL, Integer, Long>
* Q2 part 1 - What is the type of the value that is passed to doInBackground() in the AsyncTask?
* A2 part 1 - URL
*
* Q2 part 2 - What is the type of the value that is passed to the callback that reports the progress of the task?
* A2 part 2 - Integer
*
* Q2 part 3 - What is the type of the value that is passed to the callback that is executed when the task completes?
* A2 part 3 - Long
*
* Q3 - To report progress of the work executed by an AsyncTask, what callback method do you implement, and what method do you call in your AsyncTask subclass?
* A3 -Implement onProgressUpdate(). Call publishProgress().
* */

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;
    private static final String TEXT_STATE = "CurrentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

        if (savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        new SimpleAsyncTask(mTextView, mProgressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
    }
}
