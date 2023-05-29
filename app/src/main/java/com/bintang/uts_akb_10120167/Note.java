package com.bintang.uts_akb_10120167;

/*
Nama    : Bintang Zulhikman Hakim
NIM     : 10120167
Kelas   : IF-4
Matkul  : Aplikasi Komputer Bergerak
*/
public class Note {
    private long ID;
    private String title;
    private String categories;
    private String notes;
    private String date;
    private String time;

    Note(){ }
    Note(String title, String categories, String notes, String date, String time){
        this.title = title;
        this.categories = categories;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }
    Note(long id, String title, String categories, String notes, String date, String time){
        this.ID = id;
        this.title = title;
        this.categories = categories;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
