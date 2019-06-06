package me.mahakagg.notesroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;

    /*
     * constructor; gets reference to NoteRepository and gets list of all Notes from the repository
     * */
    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    /*
     * "getter" methods to get all the Notes from the repository and insert Notes into the database;
     *This hides implementation from the UI
     * */
    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note Note) {
        mRepository.insert(Note);
    }
}
