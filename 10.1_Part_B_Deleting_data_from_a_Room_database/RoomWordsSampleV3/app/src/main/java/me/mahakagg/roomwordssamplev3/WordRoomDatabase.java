package me.mahakagg.roomwordssamplev3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/*
 * Room database child class; this class is a layer on SQLiteDatabase;
 * It uses DAO for queries, doesn't allow running on main thread, and provides compile time checks for SQL
 * statements.
 * Must be abstract and only one required for the whole app
 * */

/*
 * @Database annotation tells that this is a room database; it consists of only one entity(word.class);
 * version number is 1; export schema keeps history of schema versions (disabled because database migration not needed)
 * */
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    /*
     * all the DAOs that work with the database come here;
     * Here, wordDao() works like a 'getter' for the DAO
     * */
    public abstract WordDao wordDao();

    /*
     * Making the database singleton so that there is only one instance of Room database.
     * */
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // database creation goes here

                    /*
                     * use databaseBuilder() to build Room database named "word_database".
                     * It uses destructive database migration (if database schema is changed, destroy and recreate database)
                     * Destructive migration is not preferred in real world apps
                     * */
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // run asyncTask whenever app is opened
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /*
     * asyncTask to delete all existing entries and repopulating the database with the contents of string array
     * */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            if (mDao.getAnyWord().length < 1) {
                for (int i = 0; i <= words.length - 1; i++) {
                    Word word = new Word(words[i]);
                    mDao.insert(word);
                }
            }
            return null;
        }
    }
}

