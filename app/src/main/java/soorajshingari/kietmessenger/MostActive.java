package soorajshingari.kietmessenger;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.List;

public class MostActive extends Fragment {
    ListView listView;
    ArrayAdapter<String>adapter;
    int len=0;
String users[],users1[];
DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_most_active, container, false);
        users=new String[]{"1502913112","1502913113","1502913114","1502913115","1502913116","1502913117","1502913118","1502913119","1502913120","1502913121"};
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading people...");
        listView=(ListView)view.findViewById(R.id.listOfActivePeople);
/*        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.show();
                for (DataSnapshot achild:dataSnapshot.getChildren())
                {
                    users[len++]=achild.child("username").toString().replace("@gmail.com","");

                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Unable to load list", Toast.LENGTH_SHORT).show();
                Log.d("Canclled loading list","Canclled");
                progressDialog.dismiss();
            }
        });
        */

        adapter=new ArrayAdapter<String>(getContext(),R.layout.list_item_active_people,users);
        listView.setAdapter(adapter);

        return view;
    }

}
