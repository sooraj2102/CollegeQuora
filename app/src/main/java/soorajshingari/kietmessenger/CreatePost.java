package soorajshingari.kietmessenger;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CreatePost extends AppCompatActivity {
EditText subject,body;
    Button submit,choose_button;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String string_subject,string_body,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
       // getSupportActionBar().getThemedContext().setTheme(R.style.AppBarOverlay);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Posting...");
        user= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        subject=(EditText)findViewById(R.id.editText);
        body=(EditText)findViewById(R.id.editText2);
        submit=(Button)findViewById(R.id.submit);
        choose_button=(Button)findViewById(R.id.button_image);

        choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_subject=subject.getText().toString().trim();
                string_body=body.getText().toString().trim();
                if (string_subject.length()!=0||string_body.length()!=0)
                {
                    progressDialog.show();
                    uid=user.getUid();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            CreatePostConstructor createPost=new CreatePostConstructor(string_subject,string_body,uid);
                            Map<String,Object>post=createPost.toMap();
                            String key=databaseReference.child("Posts").push().getKey();
                            Map<String,Object>childUpdate=new HashMap<>();
                            childUpdate.put("/Posts/"+key,post);
                            childUpdate.put("/users/"+uid+"/My posts/"+key,post);
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
                else
                {
                    Toast.makeText(CreatePost.this, "Both the Enteries are required", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
