package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MakeNote extends AppCompatActivity {
    EditText etTitle, etNote;
    ImageView ivBack, ivSave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_note);
        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);
        ivBack = findViewById(R.id.ivBack);
        ivSave = findViewById(R.id.ivSave);
        DB db = DB.getInstance(this);
        NotesAdapter notesAdapter = new NotesAdapter(db.fetchNotes());

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Toast.makeText(MakeNote.this, "BaCK", Toast.LENGTH_SHORT).show();
            }
        });

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = String.valueOf(etTitle.getText());
                String note = String.valueOf(etNote.getText());
                DateFormat df = new SimpleDateFormat("HH:mm:ss a, dd/MM/yyyy", Locale.getDefault());
                String currentDateAndTime = df.format(new Date());
                Notes note1 = new Notes(title, note, currentDateAndTime);
                db.insertNote(note1);


                Toast.makeText(MakeNote.this, "Note is Saved.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}