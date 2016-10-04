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

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        EditText username,password;
        final String user,pass;
        username=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.pass);
            user = username.getText().toString();
            pass = password.getText().toString();
            Button login = (Button) findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog=new ProgressDialog(dashboard.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            progressDialog.cancel();
                            startActivity(new Intent(dashboard.this,Post.class));
                            finish();
                        }
                    }
                });
                thread.start();

                        }

                });

            }

    }

