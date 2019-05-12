package me.mahakagg.hellosharedprefscc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private int mColor;
    private TextView mShowCountTextView;
    private final String COUNT_KEY = "count";
    private final String COLOR_KEY = "color";
    private final String COUNT_SAVE_KEY = "count_save";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "me.mahakagg.hellosharedprefs";
    private String COUNT_EXTRA = "count_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCountTextView = findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mCount = mPreferences.getInt(COUNT_KEY, 0);
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setText(String.format("%s", mCount));
        mShowCountTextView.setBackgroundColor(mColor);
    }

    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }

    public void reset(View view) {
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        boolean countSave = mPreferences.getBoolean(COUNT_SAVE_KEY, true);
        if (countSave){
            editor.putInt(COUNT_KEY, mCount);
        }
        else{
            editor.putInt(COUNT_KEY, 0);
        }
        editor.apply();
    }

    public void onSettingsClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}