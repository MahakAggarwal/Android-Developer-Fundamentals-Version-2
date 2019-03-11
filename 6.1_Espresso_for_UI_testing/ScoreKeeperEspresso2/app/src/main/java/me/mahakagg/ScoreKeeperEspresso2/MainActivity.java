package me.mahakagg.ScoreKeeperEspresso2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/*
 * Answers to homework questions
 *
 * Q1 - Which steps do you perform to test a View interaction, and in what order?
 * A1 - Match a View, perform an action, and assert and verify the result.
 *
 * Q2 - Which of the following annotations enables an instrumented JUnit 4 test class?
 * A2 - @RunWith and @Test
 *
 * Q3 - Which method would you use to find a child View in an AdapterView?
 * A3 - onData() to load the adapter and enable the child View to appear on the screen.
 */


public class MainActivity extends AppCompatActivity {
    private int mScore1;
    private int mScore2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    static final String SCORE_STATE_1 = "Team 1 score";
    static final String SCORE_STATE_2 = "Team 2 score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);
        if (savedInstanceState != null){
            mScore1 = savedInstanceState.getInt(SCORE_STATE_1);
            mScore2 = savedInstanceState.getInt(SCORE_STATE_2);
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        }
        else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    public void decreaseScore(View view) {
        switch (view.getId()){
            case R.id.decreaseTeam1:
                if (mScore1 == 0){
                    break;
                }
                mScore1 --;
                mScoreText1.setText(String.valueOf(mScore1));
                break;

            case R.id.decreaseTeam2:
                if (mScore2 == 0){
                    break;
                }
                mScore2 --;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    public void increaseScore(View view) {
        switch (view.getId()){
            case R.id.increaseTeam1:
                mScore1 ++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;

            case R.id.increaseTeam2:
                mScore2 ++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_STATE_1, mScore1);
        outState.putInt(SCORE_STATE_2, mScore2);
    }
}
