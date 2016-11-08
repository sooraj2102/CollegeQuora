package soorajshingari.kietmessenger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class CreatePost extends AppCompatActivity {
    EditText subject, body;
    Button submit, choose_button;
    FirebaseUser user;
    ImageView photo;
    DatabaseReference databaseReference;
    public String string_subject, string_body, uid, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        // getSupportActionBar().getThemedContext().setTheme(R.style.AppBarOverlay);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Posting...");
        photo = (ImageView) findViewById(R.id.photo);
        progressDialog.setCancelable(false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        subject = (EditText) findViewById(R.id.editText);
        body = (EditText) findViewById(R.id.editText2);
        submit = (Button) findViewById(R.id.submit);
        choose_button = (Button) findViewById(R.id.button_image);

        choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_subject = subject.getText().toString().trim();
                string_body = body.getText().toString().trim();
                if (!(string_subject.isEmpty() || string_body.isEmpty())) {
                    if (!NetworkCheck.isNetworkAvailable(CreatePost.this)) {
                        Snackbar.make(findViewById(android.R.id.content), "Check Your Internet Connection!", Snackbar.LENGTH_LONG)
                                .setAction("Retry", null).show();
                    } else {
                        progressDialog.show();
                        uid = user.getUid();
                        databaseReference.child("Posts").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                key = databaseReference.child("Posts").push().getKey();
                                CreatePostConstructor createPost = new CreatePostConstructor(string_subject, string_body, uid, key);
                                Map<String, Object> post = createPost.toMap();
                                Map<String, Object> childUpdate = new HashMap<>();
                                childUpdate.put("/Posts/" + key, post);
                                childUpdate.put("/users/" + uid + "/My posts/" + key, post);
                                databaseReference.updateChildren(childUpdate);
                                progressDialog.dismiss();
                                Toast.makeText(CreatePost.this, "Your post is successfully posted!", Toast.LENGTH_SHORT).show();
                                finish();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                progressDialog.dismiss();
                                Toast.makeText(CreatePost.this, "Error in Posting", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } else {
                    // Toast.makeText(CreatePost.this, "Both the Enteries are required", Toast.LENGTH_SHORT).show();
                    Snackbar.make(findViewById(android.R.id.content), "Both Enteries are Required!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            //        textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                photo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}