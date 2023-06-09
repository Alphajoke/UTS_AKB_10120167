package com.bintang.uts_akb_10120167;

/*
Nama    : Bintang Zulhikman Hakim
NIM     : 10120167
Kelas   : IF-4
Matkul  : Aplikasi Komputer Bergerak
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDB extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notedb";
    private static final String DATABASE_TABLE = "notetable";

    //Nama kolom di database
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CATEGORIES = "categories";
    private static final String KEY_NOTES = "notes";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";


    NoteDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table
        String query = "CREATE TABLE "+ DATABASE_TABLE + "("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        KEY_TITLE + " TEXT,"+
                        KEY_CATEGORIES + " TEXT,"+
                        KEY_NOTES + " TEXT,"+
                        KEY_DATE + " TEXT,"+
                        KEY_TIME + " TEXT"+")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CATEGORIES, note.getCategories());
        c.put(KEY_NOTES, note.getNotes());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_TIME, note.getTime());

        long ID = db.insert(DATABASE_TABLE,null,c);
        Log.d("inserted","ID -> "+ ID);

        return ID;
    }

    public Note getNote(long id){
        // select * from databaseTable where id = 1
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[]{KEY_ID,KEY_TITLE,KEY_CATEGORIES,KEY_NOTES,KEY_DATE,KEY_TIME};
        Cursor cursor = db.query(DATABASE_TABLE,query,KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        return new Note(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4), cursor.getString(5));

    }
    public List<Note> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();
        //select * from database

        String query = "SELECT * FROM "+ DATABASE_TABLE + " ORDER BY "+ KEY_ID + " DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setCategories(cursor.getString(2));
                note.setNotes(cursor.getString(3));
                note.setDate(cursor.getString(4));
                note.setTime(cursor.getString(5));

                allNotes.add(note);

            }while (cursor.moveToNext());
        }
        return allNotes;
    }

    public int editNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("edited", "edited title:-> "+note.getTitle()+"\n ID ->"+note.getID());
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CATEGORIES,note.getCategories());
        c.put(KEY_NOTES,note.getNotes());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_TIME,note.getTime());

        return db.update(DATABASE_TABLE,c,KEY_ID+"=?",new String[]{String.valueOf(note.getID())});
    }

    void deleteNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
