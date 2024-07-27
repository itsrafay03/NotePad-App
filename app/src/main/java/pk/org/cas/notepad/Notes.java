package pk.org.cas.notepad;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Notes {
    int noteId = 0;
    String title;
    String note;
    String date;
    boolean favourite = false;
    boolean thrash = false;

// For DataBase
    public static final String TABLE_NAME = "Notes";
    public static final String COL_NOTE_ID = "NoteId";
    public static final String COL_TITLE = "Title";
    public static final String COL_NOTE = "Note";
    public static final String COL_DATE = "Date";

    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, COL_NOTE_ID, COL_TITLE, COL_NOTE, COL_DATE);
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String SELECT_ALL_NOTES = "SELECT * FROM "+TABLE_NAME;

    // Constructors.
    public Notes() {
    }

    public Notes(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public Notes(int noteId,String title, String note) {
        this.noteId = noteId;
        this.title = title;
        this.note = note;
    }

    public Notes(String title, String note, String date) {
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public Notes(int noteId, String title, String note, String date) {
        this.noteId = noteId;
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public Notes(int noteId, String title, String note, String date, boolean favourite, boolean thrash) {
        this.noteId = noteId;
        this.title = title;
        this.note = note;
        this.date = date;
        this.favourite = favourite;
        this.thrash = thrash;
    }

    // Getters Setters.
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean isThrash() {
        return thrash;
    }

    public void setThrash(boolean thrash) {
        this.thrash = thrash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notes)) return false;
        Notes notes = (Notes) o;
        return getNoteId() == notes.getNoteId() && isFavourite() == notes.isFavourite() && isThrash() == notes.isThrash() && Objects.equals(getTitle(), notes.getTitle()) && Objects.equals(getNote(), notes.getNote()) && Objects.equals(getDate(), notes.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoteId(), getTitle(), getNote(), getDate(), isFavourite(), isThrash());
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteId=" + noteId +
                ", Title='" + title + '\'' +
                ", Note='" + note + '\'' +
                ", date='" + date + '\'' +
                ", favourite=" + favourite +
                ", thrash=" + thrash +
                '}';
    }
}
