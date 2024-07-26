package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteNotes extends AppCompatActivity {
    ImageView ivBack;
    RecyclerView rvFav;
    FavouriteAdapter favouriteAdapter;
    DB db = DB.getInstance(this);

    public static List<Notes> favNotesList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_notes);
        rvFav = findViewById(R.id.rvFav);
        ivBack = findViewById(R.id.ivBackFav);


        refreshFavRecyclerView();



        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshFavRecyclerView();
    }


    public void refreshFavRecyclerView(){
        favouriteAdapter = new FavouriteAdapter(db.fetchFavourite());
        rvFav.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvFav.setAdapter(favouriteAdapter);
        rvFav.setHasFixedSize(true);
        favouriteAdapter.setOnItemClickListener(new FavouriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FavouriteAdapter.FavpuriteViewHolder holder, int position) {
                List<Favourite> favourites = db.fetchFavourite();
                Intent intent = new Intent(FavouriteNotes.this, OpenFavNotes.class);
                intent.putExtra("Position", position);
                intent.putExtra("Title", favourites.get(position).getTitle());
                intent.putExtra("Note", favourites.get(position).getNote());
                startActivity(intent);
            }
        });
    }
}

