package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    FloatingActionButton addNote;
    DB db = DB.getInstance(this);
    boolean isLinear = true;
    ImageView ivlayout, ivmenu, ivProfilePic;
    TextView tvUserName, tvEmail;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMain);
        addNote = findViewById(R.id.fab_Add);
        ivlayout = findViewById(R.id.ivLayout);
        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        ivmenu = findViewById(R.id.ivMenu);
        navigationView = findViewById(R.id.navigationView);



        refreshRecyclerView();

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MakeNote.class);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Moving to Make note Activity.", Toast.LENGTH_SHORT).show();
            }
        });


        ivlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLinear){
                    ivlayout.setImageResource(R.drawable.grid);
                    refreshRecyclerView();
                    isLinear = false;
                }else {
                    ivlayout.setImageResource(R.drawable.linear);
                    recyclerView.setAdapter(notesAdapter);
                    refreshRecyclerView();
                    isLinear = true;

                }
            }
        });


        // For Navigation Drawer.
        ivmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerLayout);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.nav_home) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_favourite) {
                    Intent intent = new Intent(MainActivity.this, FavouriteNotes.class);
                    startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_setting) {
                    Intent intent = new Intent(MainActivity.this, Settings.class);
                    startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_share) {
                    Intent shareApp = new Intent();
                    shareApp.setAction(Intent.ACTION_SEND);
                    shareApp.putExtra(Intent.EXTRA_TEXT, "App Name : NotePad\nMade By : Muhammad Rafay\nVersion : 1st\n\nIt is one of the most highly recommanded app all over the Play store, so you should keep this NotePad App in your smart phones. \n\n Download:\nhttps://drive.google.com/file/d/1mB8iFQfuuCby4F0yoJRnoJigny5zWDzt/view?usp=drivesdk");
                    shareApp.setType("text/plane");
                    shareApp = Intent.createChooser(shareApp, "Share App : ");
                    startActivity(shareApp);
                }
                if(menuItem.getItemId() == R.id.nav_privacyPolicy) {
                    Intent intent = new Intent(MainActivity.this, PrivacyPolicy.class);
                    startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_aboutUs) {
                    Intent intent = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_rateUs) {
                    rateUsDialog();
                }
                if(menuItem.getItemId() == R.id.nav_profile) {
                    Intent intent = new Intent(MainActivity.this, Profile.class);
                    startActivity(intent);
                }
                if(menuItem.getItemId() == R.id.nav_logout) {
                    Toast.makeText(MainActivity.this, "You are Logout from NotePad App", Toast.LENGTH_SHORT).show();
                }
                closeDrawer(drawerLayout);
                return true;
            }
        });

        View headerView = navigationView.getHeaderView(0);
        ivProfilePic = headerView.findViewById(R.id.ivProfilePhoto);
        tvUserName = headerView.findViewById(R.id.tvUsername);
        tvEmail = headerView.findViewById(R.id.tvEmail);


        try {

            List<User> users = db.fetchUsers();
            User user = users.get(0);
            tvUserName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            ivProfilePic.setImageBitmap(user.getProfilePic());
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

//        String name = db..getName().toString();
//        String email = db.fetchLastUser().getEmail().toString();
//        tvUserName.setText(name);
//        tvEmail.setText(email);

//        User user = db.fetchUser(1);
//        tvUserName.setText(user.getName().toString());
//        tvEmail.setText(user.getEmail().toString());




    }


    public void refreshRecyclerView(){
        notesAdapter = new NotesAdapter(db.fetchNotes());
        if(isLinear){
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        }else{
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        }
        recyclerView.setAdapter(notesAdapter);
        recyclerView.setHasFixedSize(true);
        notesAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NotesAdapter.NoteViewHolder holder, int position) {
                List<Notes> notes = db.fetchNotes();
                Intent intent = new Intent(MainActivity.this, OpenNote.class);
                intent.putExtra("Position", position);
                intent.putExtra("Title", notes.get(position).getTitle());
                intent.putExtra("Note", notes.get(position).getNote());
                startActivity(intent);

                Toast.makeText(MainActivity.this, "Moving to Open note Activity.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Theme Colors Change.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.light){
            recyclerView.setBackgroundResource(R.color.light);
        }
        if(item.getItemId() == R.id.dark){
            recyclerView.setBackgroundResource(R.color.dark);
        }
        return true;
    }


    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshRecyclerView();
    }

    public void rateUsDialog(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_rate_dialog);
        AppCompatButton btnLater = dialog.findViewById(R.id.btnLater);
        AppCompatButton btnRateApp = dialog.findViewById(R.id.btnRateApp);

        btnRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Thanks a lot for Rating our NotePad App.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // dialog box close.
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}