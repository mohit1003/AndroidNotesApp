package com.mavit.notesapp;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.mavit.notesapp.listeners.NotesListener;
import com.mavit.notesapp.model.Notes;
import com.mavit.notesapp.model.NotesViewModel;
import com.mavit.notesapp.ui.NotesViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesListener {
    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_UPDATE_NOTE = 2;

    private int notesClickedPosition = -1;
    public NotesViewModel notesViewModel;
    NotesViewAdapter notesViewAdapter;
    List<Notes> allNotes;
    List<Notes> noteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewAdapter = new NotesViewAdapter(this, this);
        noteList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        EditText searchBox = findViewById(R.id.inputSearch);
        recyclerView.setAdapter(notesViewAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        ImageView addNoteMainFAB = findViewById(R.id.ImageNoteAddMain);
        addNoteMainFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToAddNote = new Intent(MainActivity.this, AddNotesActivity.class);
                startActivityForResult(
                       intentToAddNote,
                        REQUEST_CODE_ADD_NOTE
                );
            }
        });
        getNotes();

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                notesViewAdapter.cancelTimer();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(notesViewAdapter.notesList.size() > 0){
                    notesViewAdapter.searchNotes(s.toString());
                }
            }
        });



    }
    public void getNotes(){         //Fetch all notes from the notes database
        notesViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                notesViewAdapter.setNotes(notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD_NOTE && resultCode == RESULT_OK && data != null){
//            assert data != null;
            Notes note = new Notes(data.getStringExtra(AddNotesActivity.EXTRA_REPLY_TITLE),
                    data.getStringExtra(AddNotesActivity.EXTRA_REPLY_NOTE));
//                    allNotes.add(note);
            notesViewModel.insert(note);

        }
        else if(requestCode == REQUEST_CODE_UPDATE_NOTE && resultCode == RESULT_OK){
            assert data != null;

            if(data.getBooleanExtra("isNoteDelete", false)){
                notesViewAdapter.notifyDataSetChanged();
            }

            Notes note = new Notes(data.getStringExtra(AddNotesActivity.EXTRA_REPLY_TITLE),
                    data.getStringExtra(AddNotesActivity.EXTRA_REPLY_NOTE));
            allNotes = new ArrayList<Notes>();
            allNotes.add(note);
            notesViewAdapter.notesList.remove(notesClickedPosition);
            notesViewAdapter.notesList.add(notesClickedPosition,allNotes.get(0));
            notesViewAdapter.setNotes(notesViewAdapter.notesList);
//            Log.d("NOTE_DETAIL" ,this.allNotes.get(0).getNote());//


        }
//        else if(requestCode == REQUEST_CODE_DELETE && resultCode==RESULT_OK){
//            assert data != null;
//           Log.d("DELETE_PROGRESS", data.toString());
//        }
        else{
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    @Override
    public void onNoteClicked(Notes note, int position) {
        notesClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(), AddNotesActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("note", note);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_NOTE);

    }

//    @Override
//    public void onDeleteNotePressed(Notes note) {
//        notesViewModel.deleteNote(note);
//    }
}