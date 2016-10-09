package soorajshingari.kietmessenger;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class CreatePost extends AppCompatActivity {
EditText subject,body;
    Button submit;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String string_subject,string_body,string_subject1,string_body1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Create Post");
        user= FirebaseAuth.getInstance().getCurrentUser();
        subject=(EditText)findViewById(R.id.editText);
        body=(EditText)findViewById(R.id.editText2);
        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_subject=subject.getText().toString().trim();
                string_body=body.getText().toString().trim();
                if (string_subject.length()!=0||string_body.length()!=0)
                {

                 //   databaseReference.child("users").child(user.getUid()).child("Post").setValue(createPost);

                }
                else
                {
                    Toast.makeText(CreatePost.this, "Both the Enteries are required", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
