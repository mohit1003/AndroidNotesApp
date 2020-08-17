package com.mavit.notesapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mavit.notesapp.util.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NotesRepository notesRepository;
    private LiveData<List<Notes>> allNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        allNotes = notesRepository.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes(){
        return allNotes;
    }

    public void insert(Notes notes){
        notesRepository.insert(notes);
    }
//    public void deleteNote(Notes note){ notesRepository.deleteNote(note);}
}
