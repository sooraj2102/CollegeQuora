package soorajshingari.kietmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class CreatePost extends AppCompatActivity {
EditText subject,body;
    String string_subject,string_body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Create Post");
        subject=(EditText)findViewById(R.id.editText);
        body=(EditText)findViewById(R.id.editText2);
        string_subject=subject.getText().toString();
        string_body=body.getText().toString();
    }
}
