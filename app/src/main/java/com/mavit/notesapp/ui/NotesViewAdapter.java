package com.mavit.notesapp.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mavit.notesapp.R;
import com.mavit.notesapp.listeners.NotesListener;
import com.mavit.notesapp.model.Notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class NotesViewAdapter extends RecyclerView.Adapter<NotesViewAdapter.NotesViewHolder> {
    private final LayoutInflater notesInflater;
    public List<Notes> notesList;
    private NotesListener notesListener;
    private Timer timerTick;
    private List<Notes>noteSource;

    public NotesViewAdapter(Context context,  NotesListener notesListener) {
      notesInflater = LayoutInflater.from(context);
       this.notesListener = notesListener;

    }



    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = notesInflater.inflate(R.layout.notes_container_recycler_view, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {

        if(notesList != null){
//            final SelectionModel selectionModel = notesList.get(position);
            final Notes current = notesList.get(position);
            holder.title.setText(current.getTitle());
            holder.note.setText(current.getNote());

//            holder.note.setText(" --"+position);

            holder.layoutNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesListener.onNoteClicked(notesList.get(position), position);
                }
            });
//

//            holder.layoutNote.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    selectionModel.setSelected(!selectionModel.isSelected());
//                    holder.layoutNote.setBackgroundColor(selectionModel.isSelected() ?
//                            Color.parseColor("#333333") : Color.parseColor("#7B7B7B"));
//                    return false;
//                }
//            });
        }



    }

    public void searchNotes(final String searchKeyword){
                noteSource = notesList;

                ArrayList<Notes> temp = new ArrayList<>();
                for (Notes note : noteSource) {
                    if (note.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())
                            || note.getNote().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        temp.add(note);
                    }

                }
                noteSource = temp;

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    
                    notifyDataSetChanged();
                }
            });


    }

//    public void cancelTimer(){
//        if(timerTick != null){
//            timerTick.cancel();
//        }
//    }

    public void setNotes(List<Notes> notes){
        notesList = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       if(notesList != null){
           return notesList.size();
       }
       else return 0;
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView title, note;
        public LinearLayout layoutNote;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextTitle);
            note = itemView.findViewById(R.id.TextNote);
            layoutNote = itemView.findViewById(R.id.LayoutNote);


        }
    }
}
