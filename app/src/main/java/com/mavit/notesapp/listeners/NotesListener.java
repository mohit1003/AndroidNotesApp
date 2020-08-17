package com.mavit.notesapp.listeners;

import com.mavit.notesapp.model.Notes;

public interface NotesListener {
    void onNoteClicked(Notes note, int position);
}
