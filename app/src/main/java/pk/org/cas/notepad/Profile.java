package pk.org.cas.notepad;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Profile extends AppCompatActivity {
    ImageView ivPhoto, ivBack;
    EditText etName, etEmail;
    AppCompatButton btnSaveProfile, btnAddPic;

    private static final int REQUEST_CODE_IMAGE_CAPTURE = 123;
    private Uri imagePath;
    private Bitmap imageToStore;

    DB db = DB.getInstance(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivPhoto = findViewById(R.id.ivProfilePhoto);
        ivBack = findViewById(R.id.ivBackProfile);
        etName = findViewById(R.id.etProfileUsername);
        etEmail = findViewById(R.id.etProfileEmail);
        btnSaveProfile = findViewById(R.id.btnProfileSave);
        btnAddPic = findViewById(R.id.btnAddPic);
        DB db = DB.getInstance(this);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                User user = new User(name, email, imageToStore);
                List<User> users = db.fetchUsers();
                if(users.size() > 0){
                    user.setUserId(users.get(0).getUserId());
                    db.updateUser(user);
                    Toast.makeText(Profile.this, "Profile Updated.", Toast.LENGTH_SHORT).show();
                }else{
                    db.insertUser(user);
                    Toast.makeText(Profile.this, "Profile Saved.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE);
                }catch (Exception ex){
                    Toast.makeText(Profile.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //it holds the image in IV after image is selected.
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==REQUEST_CODE_IMAGE_CAPTURE && resultCode==RESULT_OK){
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                ivPhoto.setImageBitmap(imageToStore);

            }
        }catch (Exception ex){
            Toast.makeText(Profile.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
