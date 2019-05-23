package me.mahakagg.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* Entity class for Room database
* Room annotations used so that the entity can be used in the database
* */

/*
* @Entity --> represents an entity in a table; this annotation shows that this class in an entity
* table name is specified if it is different from class name
* */
@Entity(tableName = "word_table")
public class Word {

    /*
    * String mWord acts as the primary key in the table and the value can never be null
    * Column name is specified as it is different from the variable name
    * */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    /*
    * public constructor
    * */
    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }

    /*
    * getter for member variable
    * */
    public String getWord() {
        return this.mWord;
    }
}
