package me.mahakagg.simpleasynctaskcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mProgressTextView;
    private static final String TEXT_STATE = "CurrentText";
    private static final String TEXT_PROGRESS = "CurrentSleepDuration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressTextView = findViewById(R.id.textViewResult);

        if (savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressTextView.setText(savedInstanceState.getString(TEXT_PROGRESS));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        new SimpleAsyncTask(mTextView, mProgressTextView).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putString(TEXT_PROGRESS, mProgressTextView.getText().toString());
    }
}
