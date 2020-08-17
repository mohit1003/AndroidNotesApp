package com.mavit.notesapp.util;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mavit.notesapp.data.NotesDao;
import com.mavit.notesapp.data.NotesDatabase;
import com.mavit.notesapp.model.Notes;

import java.util.List;

public class NotesRepository {
    private NotesDao notesDao;
    private LiveData<List<Notes>> allNotes;


    public NotesRepository(Application application) {
        NotesDatabase notesDatabase = NotesDatabase.getDatabase(application);
        notesDao = notesDatabase.notesDao();
        allNotes = notesDao.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

    public void insert(Notes note){
        new InsertAsyncTask(notesDao).execute(note);
    }
//    public void deleteNote(Notes note){ new DeleteAsyncTask(notesDao).execute(note).execute(); }
    


    public static class InsertAsyncTask extends AsyncTask<Notes,Void,Void> {
        private NotesDao asynctaskDao;
        public InsertAsyncTask(NotesDao dao) {
            asynctaskDao = dao;

        }

        @Override
        protected Void doInBackground(Notes... params) {
            asynctaskDao.insert(params[0]);
            return null;
        }
    }



    


}
