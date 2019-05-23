package me.mahakagg.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

/*
 * DAO (Data Access Object) class; Here, SQL queries are declared and method calls are associated with it.
 * All queries to be executed on a worker thread.
 * DAO must be an abstract class or an interface
 * */

/*
* @Dao annotation used to tell Room that this is DAO class
* */
@Dao
public interface WordDao {

    /*
    * method to insert one word
    * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    /*
    * method to delete all items from the table; since there is no generic method, a query has to be made
    * */
    @Query("DELETE FROM word_table")
    void deleteAll();

    /*
    * get all the words from the table in List object
    * LiveData is used for data observation and making app responsive to data changes
    * */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
