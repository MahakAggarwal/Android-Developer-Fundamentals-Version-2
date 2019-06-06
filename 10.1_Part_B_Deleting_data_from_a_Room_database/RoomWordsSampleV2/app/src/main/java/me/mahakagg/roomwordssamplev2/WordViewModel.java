package me.mahakagg.roomwordssamplev2;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

/*
* ViewModel provides data to the UI and survives configuration changes
* Note - never pass context into ViewModel instances; Never store Activity/Fragment/View instances or context in ViewModel
* */
public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    /*
    * constructor; gets reference to WordRepository and gets list of all words from the repository
    * */
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    /*
    * "getter" methods to get all the words from the repository and insert words into the database;
    *This hides implementation from the UI
    * */
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void deleteWord(Word word) {
        mRepository.deleteWord(word);
    }
}
