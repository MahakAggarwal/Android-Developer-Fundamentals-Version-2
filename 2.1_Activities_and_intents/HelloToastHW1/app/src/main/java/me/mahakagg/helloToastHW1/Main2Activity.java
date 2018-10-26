package me.mahakagg.helloToastHW1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        int mCount = intent.getIntExtra(MainActivity.EXTRA_COUNT, 0);
        TextView mCountTextView = findViewById(R.id.count_text);
        mCountTextView.setText(String.valueOf(mCount));
    }
}
