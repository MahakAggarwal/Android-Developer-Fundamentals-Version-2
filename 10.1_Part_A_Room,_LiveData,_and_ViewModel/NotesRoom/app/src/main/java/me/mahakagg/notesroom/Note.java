package me.mahakagg.notesroom;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The Note entity class
 */
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String content;

    /**
     * Instantiates a new Note.
     *
     * @param id      the id
     * @param title   the title
     * @param content the content
     */
    public Note(int id, @NonNull  String title, @NonNull String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    @NonNull
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(@NonNull String content) {
        this.content = content;
    }
}
