package me.mahakagg.notesroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    private EditText mTitleEditText;
    private EditText mContentEditText;
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mTitleEditText = findViewById(R.id.titleEditText);
        mContentEditText = findViewById(R.id.contentEditText);
        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    }

    public void onSaveClick(View view){
        Note note = new Note(0, mTitleEditText.getText().toString(), mContentEditText.getText().toString());
        viewModel.insert(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClearClick(View view) {
        mTitleEditText.setText("");
        mContentEditText.setText("");
    }
}
