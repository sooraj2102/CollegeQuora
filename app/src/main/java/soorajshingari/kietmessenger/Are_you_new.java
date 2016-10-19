package soorajshingari.kietmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Are_you_new extends AppCompatActivity {
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_are_you_new);
        firebaseAuth=FirebaseAuth.getInstance();
       if(firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(this,Post.class));
        finish();
        }
        Button yes,no;
        yes=(Button)findViewById(R.id.yes);
        no=(Button)findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Are_you_new.this,dashboard.class));
                finish();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Are_you_new.this,Register.class));
                finish();
            }
        });
    }
}
