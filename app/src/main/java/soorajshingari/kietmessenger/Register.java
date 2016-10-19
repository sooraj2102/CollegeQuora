package soorajshingari.kietmessenger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
FirebaseAuth firebaseAuth;FirebaseAuth.AuthStateListener authStateListener;
    DatabaseReference databaseReference;
    FirebaseUser firebaseuser;
    String s_username,s_pass,s_confirm_pass;
    EditText username,pass,confrim_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();
        username=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        confrim_pass=(EditText)findViewById(R.id.confirmpass);
authStateListener=new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
            Log.d("user","0");
        else
            Log.d("user","1");
    }
};

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Signing up...");
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_username=username.getText().toString().trim()+"@gmail.com";
                s_pass=pass.getText().toString().trim();
                s_confirm_pass=confrim_pass.getText().toString().trim();
                if (s_username.length()==10) {
               //Log.d("Register",s_username.length()+"");
                    username.setError("Required Field!");
                }
                if (s_pass.length()==0) {
                   // Log.d("Register",s_pass.length()+"");
                    pass.setError("Required Field!");
                }
                if (s_confirm_pass.length()==0) {

                    confrim_pass.setError("Required Field!");
                }
                if(s_pass.length()<6)
                    pass.setError("Password should contain minimum 6 characters");
                if(s_pass.equals(s_confirm_pass)&& s_pass.length()>5)
                {
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(s_username,s_pass).addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Log.d("Register","0");

                            if(task.isSuccessful())
                            {progressDialog.dismiss();
                                Log.d("Register ","1");
                                Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                User user=new User(s_username,s_pass);
                                firebaseuser=firebaseAuth.getCurrentUser();
                                databaseReference.child("users").child(firebaseuser.getUid()).setValue(user);
                                startActivity(new Intent(Register.this,Post.class));
                                finish();
                            }
                            else {
                                progressDialog.dismiss();
                                Log.d("Register","2");
                                Toast.makeText(Register.this, "Error signing up", Toast.LENGTH_SHORT).show();
                               // User user=new User(s_username,s_pass);
                                //databaseReference.child("users").setValue(user);

                            }
                        }
                    });
                }
                if(!s_pass.equals(s_confirm_pass))  {
                    confrim_pass.setError("Your password and confirm password is no matching!");
                    confrim_pass.setText("");
                    pass.setText("");
                }
            }
        });



    }
}
