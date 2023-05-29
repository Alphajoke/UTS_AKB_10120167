package com.rizkyrazak.uts_akb_10120167;

/* NIM      : 10119118
 * NAMA      : Rizky Septiana Abdul Razak
 * KELAS     : IF - 3 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditNotesActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteCategories, notes;
    TextView date;
    Calendar c;
    String bulan;
    String todaysDate;
    String currentTime;
    NoteDB db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID", 0);
        db = new NoteDB(this);
        note = db.getNote(id);


        toolbar = findViewById(R.id.toolbarEditNote);
        date = findViewById(R.id.noteDateEdit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(note.getTitle());
        noteTitle = findViewById(R.id.noteTitleEdit);
        noteCategories = findViewById(R.id.noteCategoriesEdit);
        notes = findViewById(R.id.noteEdit);

        noteTitle.setText(note.getTitle());
        noteCategories.setText(note.getCategories());
        notes.setText(note.getNotes());

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    getSupportActionBar().setTitle(charSequence);
                } else {
                    getSupportActionBar().setTitle("Catatan Baru");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //waktu hari ini
        c = Calendar.getInstance();
        Integer bln = (c.get(Calendar.MONTH) + 1);
        if (bln == 1) {
            bulan = "Jan";
        } else if (bln == 2) {
            bulan = "Feb";
        } else if (bln == 3) {
            bulan = "Mar";
        } else if (bln == 4) {
            bulan = "Apr";
        } else if (bln == 5) {
            bulan = "Mei";
        } else if (bln == 6) {
            bulan = "Jun";
        } else if (bln == 7) {
            bulan = "Jul";
        } else if (bln == 8) {
            bulan = "Agu";
        } else if (bln == 9) {
            bulan = "Sep";
        } else if (bln == 10) {
            bulan = "Okt";
        } else if (bln == 11) {
            bulan = "Nov";
        } else if (bln == 12) {
            bulan = "Des";
        }
        todaysDate = c.get(Calendar.DAY_OF_MONTH) + " " + bulan + " " + c.get(Calendar.YEAR);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
        date.setText(todaysDate);
        Log.d("Kalender", "Tanggal dan waktu : " + todaysDate + " dan " + currentTime);


    }

    private String pad(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            note.setTitle(noteTitle.getText().toString());
            note.setNotes(notes.getText().toString());
            int id = db.editNote(note);
            Toast.makeText(this, "Catatan Dirubah", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), NotesDetail.class);
            i.putExtra("ID", note.getID());
            startActivity(i);
        }
        if (item.getItemId() == R.id.close) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


