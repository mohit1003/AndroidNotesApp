package com.mavit.notesapp.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mavit.notesapp.model.Notes;

@Database(entities = {Notes.class}, version = 2, exportSchema = false)

public abstract class  NotesDatabase extends RoomDatabase {
    public static volatile NotesDatabase INSTANCE;
    public abstract NotesDao notesDao();

    public static NotesDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NotesDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDatabase.class, "notes_database")
//                            .addCallback(notesDatabaseCallback)
                                .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE NotesTable "
                    + " ADD COLUMN PhotoURL TEXT");
            database.execSQL("ALTER TABLE NotesTable "
                    + " ADD COLUMN WebLink TEXT");
        }
    };


    public static RoomDatabase.Callback notesDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new DeleteAll(INSTANCE).execute();
                }
            };


    private static class DeleteAll extends AsyncTask<Void,Void, Void> {
        final NotesDao asynctaskDao;

        public DeleteAll(NotesDatabase db) {
            asynctaskDao = db.notesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
           asynctaskDao.deleteAll();
           return null;
        }
    }

}


