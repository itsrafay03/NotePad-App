package pk.org.cas.notepad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OpenFavNotes extends AppCompatActivity {
    ImageView ivOpenFavBack, ivFavFav, ivFavDel, ivFavShare;
    ExtendedFloatingActionButton fab_FavUpdate;
    EditText etFavOpen_Title, etFavOpen_Note;
    DB db = DB.getInstance(this);
    boolean isFavourite = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_fav_notes);
        ivOpenFavBack = findViewById(R.id.ivOpenFav_Back);
        ivFavFav = findViewById(R.id.ivFavFav);
        ivFavDel = findViewById(R.id.ivFavDel);
        ivFavShare = findViewById(R.id.ivFavShare);
        fab_FavUpdate = findViewById(R.id.fab_FavUpdate);
        etFavOpen_Title = findViewById(R.id.etFavOpenTitle);
        etFavOpen_Note = findViewById(R.id.etFavOpenNote);

        List<Favourite> favouriteNotes = db.fetchFavourite();
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(favouriteNotes);

        Intent intent = getIntent();
        int position = intent.getIntExtra("Position",-1);
        String title = intent.getStringExtra("Title");
        String note = intent.getStringExtra("Note");


        etFavOpen_Title.setText(title);
        etFavOpen_Note.setText(note);

        // Back Button
        ivOpenFavBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Delete Button
        ivFavDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertBox();
            }
            private void openAlertBox() {
                AlertDialog.Builder builder = new AlertDialog.Builder(OpenFavNotes.this);
                builder.setTitle("Delete Favourite Note?");
                builder.setMessage("Are you sure you want to delete your Favourite note?");
                builder.setIcon(R.drawable.delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(db.deleteFavourite(favouriteNotes.get(position).getNoteId())){
                            favouriteNotes.remove(position);
                            favouriteAdapter.notifyItemRemoved(position);
                            finish();
                            Toast.makeText(OpenFavNotes.this, "Favourite Note is Deleted.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Update fab Button
        fab_FavUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = (favouriteNotes.get(position).getNoteId());
                String title = String.valueOf(etFavOpen_Title.getText());
                String note = String.valueOf(etFavOpen_Note.getText());
                Favourite favourite = new Favourite(id,title, note);
                if (db.updateFavourite(favourite)){
                    favouriteAdapter.notifyDataSetChanged();
                    favouriteAdapter.notifyItemChanged(position);
                    DateFormat df = new SimpleDateFormat("HH:mm:ss a, dd/MM/yyyy", Locale.getDefault());
                    String currentDateAndTime = df.format(new Date());
                    favourite.setDate(currentDateAndTime);
                    finish();
                    Toast.makeText(OpenFavNotes.this, "Favourite Note Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Favourite Note.
        ivFavFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavourite){
                    ivFavFav.setImageResource(R.drawable.favourites);
//                    int id = (favouriteNotes.get(position).getNoteId());
//                    String title = String.valueOf(etFavOpen_Title.getText());
//                    String note = String.valueOf(etFavOpen_Note.getText());
//                    Notes notes = new Notes(id,title, note);
//                    Favourite favourite = new Favourite(id, title, note);
//                    db.insertNote(notes);
//                    db.deleteFavourite(favourite.getNoteId());
//                    Toast.makeText(OpenFavNotes.this, "Note Removed from Favourites.", Toast.LENGTH_SHORT).show();
//                    isFavourite = false;
                }else {

                }
            }
        });


        ivFavShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, etFavOpen_Title.getText().toString()+"\n\n"+etFavOpen_Note.getText().toString());
                shareIntent.setType("text/plane");
                shareIntent = Intent.createChooser(shareIntent, "Share to : ");
                startActivity(shareIntent);
            }
        });
    }
}
