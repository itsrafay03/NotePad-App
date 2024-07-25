package pk.org.cas.notepad;

import java.util.Objects;

public class Favourite {
    int noteId = 0;
    String title;
    String note;
    String date;
    boolean favourite = false;

    // For DataBase
    public static final String TABLE_NAME = "Favourite";
    public static final String COL_NOTE_ID = "NoteId";
    public static final String COL_TITLE = "Title";
    public static final String COL_NOTE = "Note";
    public static final String COL_DATE = "Date";

    public static final String CREATE_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", TABLE_NAME, COL_NOTE_ID, COL_TITLE, COL_NOTE);
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String SELECT_ALL_NOTES = "SELECT * FROM "+TABLE_NAME;

    public Favourite() {
    }
    public Favourite(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public Favourite(int noteId,String title, String note) {
        this.noteId = noteId;
        this.title = title;
        this.note = note;
    }

    public Favourite(String title, String note, String date) {
        this.title = title;
        this.note = note;
        this.date = date;
    }
    public Favourite(int noteId, String title, String note, String date, boolean favourite) {
        this.noteId = noteId;
        this.title = title;
        this.note = note;
        this.date = date;
        this.favourite = favourite;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favourite)) return false;
        Favourite favourite1 = (Favourite) o;
        return noteId == favourite1.noteId && favourite == favourite1.favourite && Objects.equals(title, favourite1.title) && Objects.equals(note, favourite1.note) && Objects.equals(date, favourite1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, title, note, date, favourite);
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "noteId=" + noteId +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                ", favourite=" + favourite +
                '}';
    }
}
