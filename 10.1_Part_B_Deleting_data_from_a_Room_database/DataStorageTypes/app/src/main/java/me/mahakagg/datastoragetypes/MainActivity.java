package me.mahakagg.datastoragetypes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
 * answers to homework questions
 *
 * Q1 - Android Architecture Components provide some convenience annotations for DAOs.
 *      Which of the following are available?
 * A1 - @Dao, @Insert, @Delete, @Update
 *
 * Q2 - What are the benefits of using Architecture Components?
 * A2 - Architecture Components help you structure your app in a way that is robust and testable.
 *      Architecture Components provide a simple, flexible, and practical approach to structuring your app.
 *      If you use the provided libraries and architecture, your app is more maintainable with less boilerplate code.
 * */

// this app demonstrates 3 types of storage methods - savedInstanceState, Room Database and shared preferences
public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    private EditText mInstanceStateEditText;
    private EditText mSharedPrefEditText;
    private EditText mRoomEditText;
    private TextView mInstanceStateTextView;
    private TextView mSharedPrefTextView;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile = "me.mahakagg.datastoragetypes";
    private String instanceStateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find views by ID for textViews and editTexts
        mInstanceStateEditText = findViewById(R.id.instanceStateEditText);
        mSharedPrefEditText = findViewById(R.id.sharedPrefEditText);
        mRoomEditText = findViewById(R.id.roomEditText);
        mInstanceStateTextView = findViewById(R.id.instanceStateTextView);
        mSharedPrefTextView = findViewById(R.id.sharedPrefTextView);

        // setup viewModel and sharedPreferences
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        sharedPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // recyclerView setup
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get all words from view models
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                adapter.setWords(words);
            }
        });

        // set text for value saved using shared preferences
        mSharedPrefTextView.setText("Text Saved: " + sharedPreferences.getString("sharedPrefString", ""));

        // set text for value saved using save instance state
        if (savedInstanceState!= null){
            mInstanceStateTextView.setText("Text Saved: " + savedInstanceState.getString("instanceStateKey", ""));
        }
    }

    public void onSaveClick(View view) {
        // take all values from editTexts and put them in their respective TextViews
        instanceStateText = mInstanceStateEditText.getText().toString();
        mInstanceStateTextView.setText("Text Saved: " + instanceStateText);

        String sharedPrefText = mSharedPrefEditText.getText().toString();
        mSharedPrefTextView.setText("Text Saved: " + sharedPrefText);

        // save value to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sharedPrefString", sharedPrefText).apply();

        // insert word into room database
        String roomText = mRoomEditText.getText().toString();
        Word word = new Word(roomText);
        mWordViewModel.insert(word);

        // clear all editTexts after saving all the information
        mInstanceStateEditText.setText("");
        mSharedPrefEditText.setText("");
        mRoomEditText.setText("");
    }

    // save instance state implementation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("instanceStateKey", instanceStateText);
    }
}
