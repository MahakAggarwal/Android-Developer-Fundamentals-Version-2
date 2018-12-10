package me.mahakagg.RecyclerViewLayoutHW;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.LinkedList;
import java.util.Objects;


/**
 * Answers to questions
 *
 * Q1 - Which resource qualifier is used most frequently to select for tablets?
 * A1 - Smallest screen width
 *
 * Q2 - Which folder would hold the strings.xml file for translation into French for Canada?
 * A2 - res/values-fr-rCA/
 *
 * Q3 - Which folder is for XML files that contain strings, integers, and colors?
 * A3 - res/values
 */


public class MainActivity extends AppCompatActivity {
    // LinkedList to hold the data
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int gridCount = getResources().getInteger(R.integer.grid_column_count);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // add new word to the linked list
                mWordList.addLast("+ Word " + wordListSize);
                // notify adapter of data change
                Objects.requireNonNull(mRecyclerView.getAdapter()).notifyItemInserted(wordListSize);
                // scroll to the bottom of the list
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        // populate the initial data
        for (int i = 1; i < 21; i++) {
            mWordList.add("Word " + i);
        }

        // set up recyclerView - find view, create and set adapter; set layout manager
        mRecyclerView = findViewById(R.id.recyclerView);
        WordListAdapter mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mWordList.clear();
            for (int i = 1; i < 21; i++) {
                mWordList.add("Word " + i);
            }
            Objects.requireNonNull(mRecyclerView.getAdapter()).notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
