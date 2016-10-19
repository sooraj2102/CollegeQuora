package soorajshingari.kietmessenger;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Comments extends AppCompatActivity {
    RecyclerView recyclerview;
    DatabaseReference databaseReference;

    RecyclerView.Adapter adapter;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comments);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Posts");
        List<String>list=new ArrayList<>();

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
       // getSupportActionBar().getThemedContext().setTheme(R.style.AppBarOverlay);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview_comments);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setHasFixedSize(true);

        }



}
