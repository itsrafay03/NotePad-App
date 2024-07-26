package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {
    private static DB instance;
    public static final String DB_NAME = "NOTEPAD";
    public static final int DB_VERSION = 13;




    private DB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DB getInstance(Context context){
        if(instance == null){
            instance = new DB(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Notes.CREATE_TABLE);
        db.execSQL(User.CREATE_TABLE);
        db.execSQL(Favourite.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if((oldVersion != newVersion)){
            db.execSQL(Notes.DROP_TABLE);
            db.execSQL(Notes.CREATE_TABLE);

            db.execSQL(User.DROP_TABLE);
            db.execSQL(User.CREATE_TABLE);

            db.execSQL(Favourite.DROP_TABLE);
            db.execSQL(Favourite.CREATE_TABLE);

        }
    }

    // Crude Operations of Note.
    public boolean insertNote(Notes note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.COL_TITLE, note.getTitle());
        contentValues.put(Notes.COL_NOTE, note.getNote());
        long rowID;
        try {
            rowID = db.insert(Notes.TABLE_NAME, null, contentValues);
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean updateNote(Notes note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.COL_TITLE, note.getTitle());
        contentValues.put(Notes.COL_NOTE, note.getNote());
        long rowID;
        try{
            rowID = db.update(Notes.TABLE_NAME, contentValues, Notes.COL_NOTE_ID+"= ?", new String[]{String.valueOf(note.getNoteId())});
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean deleteNote(int noteId){
        SQLiteDatabase db = getWritableDatabase();
        long rowId;
        try {
            rowId = db.delete(Notes.TABLE_NAME, Notes.COL_NOTE_ID+" = ? ", new String[]{String.valueOf(noteId)});
        }catch (Exception ex){
            return false;
        }
        return rowId != -1;
    }

    public List<Notes> fetchNotes(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(Notes.SELECT_ALL_NOTES, null);
        List<Notes> notes = new ArrayList<>(cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                Notes note = new Notes();
                int index = cursor.getColumnIndex(Notes.COL_NOTE_ID);
                note.setNoteId(cursor.getInt(index));
                index = cursor.getColumnIndex(Notes.COL_TITLE);
                note.setTitle(cursor.getString(index));
                index = cursor.getColumnIndex(Notes.COL_NOTE);
                note.setNote(cursor.getString(index));
                notes.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }



    // Crude Operations of Note.
    public boolean insertFavourite(Favourite favourite){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Favourite.COL_TITLE, favourite.getTitle());
        contentValues.put(Favourite.COL_NOTE, favourite.getNote());
        long rowID;
        try {
            rowID = db.insert(Favourite.TABLE_NAME, null, contentValues);
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean updateFavourite(Favourite favourite){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Favourite.COL_TITLE, favourite.getTitle());
        contentValues.put(Favourite.COL_NOTE, favourite.getNote());
        long rowID;
        try{
            rowID = db.update(Favourite.TABLE_NAME, contentValues, Favourite.COL_NOTE_ID+"= ?", new String[]{String.valueOf(favourite.getNoteId())});
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }


    public boolean deleteFavourite(int noteId){
        SQLiteDatabase db = getWritableDatabase();
        long rowId;
        try {
            rowId = db.delete(Favourite.TABLE_NAME, Favourite.COL_NOTE_ID+" = ? ", new String[]{String.valueOf(noteId)});
        }catch (Exception ex){
            return false;
        }
        return rowId != -1;
    }

    public List<Favourite> fetchFavourite(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(Favourite.SELECT_ALL_NOTES, null);
        List<Favourite> favourites = new ArrayList<>(cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                Favourite favourite = new Favourite();
                int index = cursor.getColumnIndex(Favourite.COL_NOTE_ID);
                favourite.setNoteId(cursor.getInt(index));
                index = cursor.getColumnIndex(Favourite.COL_TITLE);
                favourite.setTitle(cursor.getString(index));
                index = cursor.getColumnIndex(Favourite.COL_NOTE);
                favourite.setNote(cursor.getString(index));
                favourites.add(favourite);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return favourites;
    }































    // Crude Operations of User.
    public boolean insertUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, user.getName());
        contentValues.put(User.COL_EMAIL, user.getEmail());
        contentValues.put(User.COL_PROFILE_PIC, getBytes(user.getProfilePic()));


//        Bitmap imageToStoreBitmap = user.getProfilePic();
//        byteArrayOutputStream = new ByteArrayOutputStream();
//        imageToStoreBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//        imageInBytes = byteArrayOutputStream.toByteArray();
//        contentValues.put(User.COL_PROFILE_PIC, imageInBytes);
        long rowID;
        try {
            rowID = db.insert(User.TABLE_NAME, null, contentValues);
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.COL_NAME, user.getName());
        contentValues.put(User.COL_EMAIL, user.getEmail());
        contentValues.put(User.COL_PROFILE_PIC, getBytes(user.getProfilePic()));

//        Bitmap imageToStoreBitmap = user.getProfilePic();
//        byteArrayOutputStream = new ByteArrayOutputStream();
//        imageToStoreBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//        imageInBytes = byteArrayOutputStream.toByteArray();
//        contentValues.put(User.COL_PROFILE_PIC, imageInBytes);
        long rowID;
        try{
            rowID = db.update(User.TABLE_NAME, contentValues, User.COL_USER_ID+"= ?", new String[]{String.valueOf(user.getUserId())});
        }catch (Exception ex){
            return false;
        }
        return rowID != -1;
    }

    public boolean deleteUser(int userId){
        SQLiteDatabase db = getWritableDatabase();
        long rowId;
        try {
            rowId = db.delete(User.TABLE_NAME, User.COL_USER_ID+" = ? ", new String[]{String.valueOf(userId)});
        }catch (Exception ex){
            return false;
        }
        return rowId != -1;
    }

    public List<User> fetchUsers(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(User.SELECT_ALL_USERS, null);
        List<User> users = new ArrayList<>(cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                int index = cursor.getColumnIndex(User.COL_USER_ID);
                user.setUserId(cursor.getInt(index));
                index = cursor.getColumnIndex(User.COL_NAME);
                user.setName(cursor.getString(index));
                index = cursor.getColumnIndex(User.COL_EMAIL);
                user.setEmail(cursor.getString(index));
                index = cursor.getColumnIndex(User.COL_PROFILE_PIC);
                user.setProfilePic(getImage(cursor.getBlob(index)));
                users.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    @SuppressLint("Range")
    public User fetchUser(int userId){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(User.SELECT_ALL_USERS, null);
        User user = new User();
        if(cursor.moveToNext()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(User.COL_USER_ID));
                if(id == userId){
                    user.setName(cursor.getString(cursor.getColumnIndex(User.COL_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(User.COL_NAME)));
                    user.setProfilePic(getImage(cursor.getBlob(cursor.getColumnIndex(User.COL_PROFILE_PIC))));

                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return user;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
