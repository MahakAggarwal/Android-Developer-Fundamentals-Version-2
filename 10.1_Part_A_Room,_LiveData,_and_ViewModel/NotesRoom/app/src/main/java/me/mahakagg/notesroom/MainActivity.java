package me.mahakagg.notesroom;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

/*
 * Answers to homework questions
 *
 * Q1 - What are the advantages of using a Room database?
 * A1 -Creates and manages an Android SQLite database for you.
 * Eliminates a lot of boilerplate code.
 * Using a DAO, provides a mechanism for mapping Java methods to database queries.
 *
 * Q2 - Which of the following are reasons for using a ViewModel?
 * A2 - Often used with LiveData for changeable data that the UI will use or display.
 * Prevents your data from being lost when the app crashes.
 * Acts as a communication center between the Repository and the UI.
 * ViewModel instances survive device configuration changes.
 *
 * Q3 - What is the DAO?
 * A3 - Short for "data access object."
 * A library for managing database queries.
 * An annotated interface that maps Java methods to SQLite queries.
 * A class whose methods run always in the background, not on the main thread.
 * A class that the compiler checks for SQL errors, then uses to generate queries from the annotations.
 *
 * Q4 - What are features of LiveData?
 * A4 - When LiveData is used with Room, data updates automatically if all the intermediate levels also return LiveData (DAO, ViewModel, Repository).
 * Uses the observer pattern and notifies its observers when its data has changed.
 * Is lifecycle aware.
 *
 * */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        // recyclerView setup
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final NoteListAdapter adapter = new NoteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteViewModel viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

    }
}
