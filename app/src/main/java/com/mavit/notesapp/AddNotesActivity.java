package com.mavit.notesapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.mavit.notesapp.data.NotesDatabase;
import com.mavit.notesapp.listeners.DeleteNoteListener;
import com.mavit.notesapp.model.Notes;
import com.mavit.notesapp.model.NotesViewModel;

//import android.widget.TextView;
//import com.mavit.notesapp.util.NotesRepository;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;

public class AddNotesActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_TITLE = "com.mohit.title";
    public static final String EXTRA_REPLY_NOTE = "com.mohit.note";
    EditText titleText, noteText;
    private String selectedColor;
    private View titleIndicator;
    private Notes alreadyAvailableNote;
    private AlertDialog deleteDialog;
    public NotesViewModel notesViewModel;
    DeleteNoteListener deleteListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedColor = "#333333";
        //        this.setTitleBarColor();
        setContentView(R.layout.activity_add_notes);
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        ImageView ImageBackIos = findViewById(R.id.ImageBack);
        ImageBackIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        titleText = findViewById(R.id.InputNoteTitle);
        noteText = findViewById(R.id.InputNote);
        final ImageView doneButton = findViewById(R.id.DoneButton);
        titleIndicator = findViewById(R.id.TitleIndicator);

        if(getIntent().getBooleanExtra("isViewOrUpdate", false)){
            alreadyAvailableNote = (Notes) getIntent().getSerializableExtra("note");
            setViewOrUpdateNote();
        }


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        changeColor();
    }



    private void setViewOrUpdateNote(){
        titleText.setText(alreadyAvailableNote.getTitle());
        noteText.setText((alreadyAvailableNote.getNote()));
//        if(alreadyAvailableNote.getImagePath() != null && !alreadyAvailableNote.getImagePath().trim().isEmpty()){
//            imageNote.setImageBitmap(BitmapFactory.decodeFile(alreadyAvailableNote.getImagePath()));
//            imageNote.setVisibility(View.VISIBLE);
//            selectImagePath = alreadyAvailableNote.getImagePath();
//        }
    }

    private void saveNote(){
        Intent replyBack = new Intent();
        if(titleText.getText().toString().trim().isEmpty()){
            Toast.makeText(AddNotesActivity.this, "Note Title cannot be empty", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, replyBack);

        }else if(noteText.getText().toString().trim().isEmpty()){
            Toast.makeText(AddNotesActivity.this, "Note cannot be empty", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, replyBack);

        }
        else {
            String textTitle = titleText.getText().toString();
            String textNote = noteText.getText().toString();
            replyBack.putExtra(EXTRA_REPLY_TITLE, textTitle);
            replyBack.putExtra(EXTRA_REPLY_NOTE, textNote);
            setResult(RESULT_OK, replyBack);
            finish();
        }
    }

    public void changeColor() {
        final ImageView color1 = findViewById(R.id.ImageColor1);
        final ImageView color2 = findViewById(R.id.ImageColor2);
        final ImageView color3 = findViewById(R.id.ImageColor3);
        final ImageView color4 = findViewById(R.id.ImageColor4);
        final ImageView color5 = findViewById(R.id.ImageColor5);
        setTitleBarColor();

        findViewById(R.id.ViewColor1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#333333";
                color1.setImageResource(R.drawable.ic_done);
                color2.setImageResource(0);
                color3.setImageResource(0);
                color4.setImageResource(0);
                color5.setImageResource(0);
                setTitleBarColor();


            }
        });
        findViewById(R.id.ViewColor2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#FDBE38";
                color1.setImageResource(0);
                color2.setImageResource(R.drawable.ic_done);
                color3.setImageResource(0);
                color4.setImageResource(0);
                color5.setImageResource(0);
                setTitleBarColor();

            }
        });
        findViewById(R.id.ViewColor3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#FF4842";
                color1.setImageResource(0);
                color2.setImageResource(0);
                color3.setImageResource(R.drawable.ic_done);
                color4.setImageResource(0);
                color5.setImageResource(0);
                setTitleBarColor();

            }
        });
        findViewById(R.id.ViewColor4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#3A52FC";
                color1.setImageResource(0);
                color2.setImageResource(0);
                color3.setImageResource(0);
                color4.setImageResource(R.drawable.ic_done);
                color5.setImageResource(0);
                setTitleBarColor();

            }
        });
        findViewById(R.id.ViewColor5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedColor = "#000000";
                color1.setImageResource(0);
                color2.setImageResource(0);
                color3.setImageResource(0);
                color4.setImageResource(0);
                color5.setImageResource(R.drawable.ic_done);
                setTitleBarColor();

            }
        });
        if(alreadyAvailableNote != null){
            findViewById(R.id.DeleteIconButton).setVisibility(View.VISIBLE);
            findViewById(R.id.DeleteIconButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDeleteDialog();
                }
            });

        }

    }

    private void showDeleteDialog(){
        if(deleteDialog == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(AddNotesActivity.this);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.more_options_note,
                    (ViewGroup) findViewById(R.id.MoreOptions)
            );
            builder.setView(view);
            deleteDialog = builder.create();
            if(deleteDialog.getWindow() != null){
                deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            view.findViewById(R.id.DeleteText).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

                        @Override
                        protected Void doInBackground(Void... voids) {
                            NotesDatabase.getDatabase(getApplicationContext()).notesDao()
                                    .deleteNote(alreadyAvailableNote);
                            return null;

                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Intent intent = new Intent();
                            intent.putExtra("isNoteDelete", true);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }

                new DeleteAsyncTask().execute();

                }
            });
            view.findViewById(R.id.CancelText).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteDialog.dismiss();
                }
            });
        }
        deleteDialog.show();
    }

    private void setTitleBarColor(){
        GradientDrawable gradientDrawable = (GradientDrawable) titleIndicator.getBackground();
        gradientDrawable.setColor(Color.parseColor(selectedColor));
    }
}