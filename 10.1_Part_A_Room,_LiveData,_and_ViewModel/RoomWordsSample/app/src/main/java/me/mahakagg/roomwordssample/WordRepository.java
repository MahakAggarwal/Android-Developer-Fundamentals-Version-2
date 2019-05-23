package me.mahakagg.roomwordssample;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/*
* this class abstracts access to multiple data sources. It handles data operations and manages query threads.
* */
public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    /*
    * constructor to get handle to the database and initialize member variables
    * */
    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    /*
    * wrapper method that returns cached words as liveData objects so they can be observed
    * */
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /*
    * wrapper for insert method which uses AsyncTask to run the query on a worker thread
    * */
    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    /*
    * AsyncTask inner class
    * */
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao wordDao) {
            mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
