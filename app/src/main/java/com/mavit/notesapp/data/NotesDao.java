package com.mavit.notesapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mavit.notesapp.model.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void insert(Notes notes);

    @Query("DELETE FROM NotesTable")
    void deleteAll();

    @Delete
    void deleteNote(Notes Note);

    @Query("DELETE FROM NotesTable WHERE id = :id")
    void delete(int id);

    @Query("UPDATE NotesTable SET Title = :title WHERE id= :id")
    void updateTitle(String title, int id);

    @Query("UPDATE NotesTable SET SubTitle = :subTitle WHERE id= :id")
    void updateSubTitle(String subTitle, int id);

//    @Query("UPDATE NotesTable SET Date = :date WHERE id= :id")
//    void updateDate(Date date, int id);

    @Query("UPDATE NotesTable SET Note = :note WHERE id= :id")
    void updateNote(String note, int id);

    @Query("SELECT * FROM NotesTable")
    LiveData<List<Notes>> getAllNotes();

}
