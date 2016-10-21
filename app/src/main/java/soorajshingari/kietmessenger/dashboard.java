package soorajshingari.kietmessenger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class dashboard extends AppCompatActivity {
DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseAuth.AuthStateListener authStateListener;
    EditText username,password;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        username=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.pass);

            Button login = (Button) findViewById(R.id.signup);
            login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString()+"@gmail.com";
                pass = password.getText().toString();
                final ProgressDialog progressDialog=new ProgressDialog(dashboard.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Authenticating...");
                if (user.length()==10) {
                    //Log.d("Register",s_username.length()+"");
                    username.setError("Required Field!");
                }
                if (pass.length()==0) {
                    // Log.d("Register",s_pass.length()+"");
                    password.setError("Required Field!");
                }
                else {
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(dashboard.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                startActivity(new Intent(dashboard.this, Post.class));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(dashboard.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                                password.setText("");
                                username.setText("");
                            }

                        }
                    });
                }




            }

            });

            }

    }

