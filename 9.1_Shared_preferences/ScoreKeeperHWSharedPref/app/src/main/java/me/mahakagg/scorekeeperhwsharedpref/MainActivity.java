package me.mahakagg.scorekeeperhwsharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mScore1;
    private int mScore2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    static final String SCORE_1_KEY = "Team 1 score";
    static final String SCORE_2_KEY = "Team 2 score";
    SharedPreferences sharedPreferences;
    private static final String sharedPrefFile = "me.mahakagg.scorekeeperhwsharedpref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mScore1 = sharedPreferences.getInt(SCORE_1_KEY, 0);
        mScore2 = sharedPreferences.getInt(SCORE_2_KEY, 0);
        mScoreText1.setText(String.valueOf(mScore1));
        mScoreText2.setText(String.valueOf(mScore2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    public void decreaseScore(View view) {
        switch (view.getId()) {
            case R.id.decreaseTeam1:
                if (mScore1 == 0) {
                    break;
                }
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;

            case R.id.decreaseTeam2:
                if (mScore2 == 0) {
                    break;
                }
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void increaseScore(View view) {
        switch (view.getId()) {
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;

            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SCORE_1_KEY, mScore1);
        editor.putInt(SCORE_2_KEY, mScore2);
        editor.apply();
    }

    public void onResetClick(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        mScore1 = 0;
        mScore2 = 0;
        mScoreText1.setText(String.valueOf(mScore1));
        mScoreText2.setText(String.valueOf(mScore2));
    }
}