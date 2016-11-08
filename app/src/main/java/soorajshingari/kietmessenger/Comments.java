package soorajshingari.kietmessenger;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Comments extends AppCompatActivity {
    RecyclerView recyclerviewOthers ,recyclerviewMy;
    DatabaseReference databaseReference;
    EditText comment;
    Button submit_comment;
    FirebaseUser firebaseUser;
    String uid ;
    String postKey="-KUeqdhjprtunGl3Rtfc",currentuser;


    RecyclerView.Adapter adapterOthers,adapterMy;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comments);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        List<String>listComments=new ArrayList<>();
        List<String>listUsers=new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        comment=(EditText)findViewById(R.id.editText_comment);
        submit_comment=(Button)findViewById(R.id.submit_comment);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
       // getSupportActionBar().getThemedContext().setTheme(R.style.AppBarOverlay);
        recyclerviewOthers=(RecyclerView)findViewById(R.id.recyclerview_comments);
        //recyclerviewMy=(RecyclerView)findViewById(R.id.recyclerview_comments);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewOthers.setLayoutManager(linearLayoutManager);
        recyclerviewOthers.setHasFixedSize(true);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        uid=firebaseUser.getUid();
       /* databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             User user=dataSnapshot.getValue(User.class);
               currentuser= user.username;
                Log.d("\n\nusers data change\n\n",currentuser);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        */
        //recyclerviewMy.setLayoutManager(linearLayoutManager);
        //recyclerviewMy.setHasFixedSize(true);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Posting Comment...");
        progressDialog.setCancelable(false);
        for (int i=0;i<20;i++)
        {
            listComments.add("Sample Comment\n"+i);
            listUsers.add("1502913097");
        }
        adapterOthers=new commentsAdapterOthers(listComments,listUsers);
        recyclerviewOthers.setAdapter(adapterOthers);
        //recyclerviewMy.setAdapter(adapterMy);
        submit_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!comment.getText().toString().trim().isEmpty()) {
                    progressDialog.show();
                    databaseReference.child("Posts").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot achild : dataSnapshot.getChildren()) {
                                CreatePostConstructor createPostConstructor=achild.getValue(CreatePostConstructor.class);

                                if (createPostConstructor.key.equals("-KUbCl3lsAbHzPXkoKey")) {
                                    postKey = achild.getKey();
                                    break;
                                }
                                Log.d("\n\nChilds" + "\n\n" , createPostConstructor.key + "\n\n");
                            }
              //              uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            comment_constructor commentConstructor = new comment_constructor(comment.getText().toString(),currentuser);
                            databaseReference.child("Comments").child(postKey).child(uid).setValue(commentConstructor);
                            progressDialog.dismiss();
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Comments.this, "Error in posting comment", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    });

                }
               // else Toast.makeText(Comments.this, "C", Toast.LENGTH_SHORT).show();
            }
        });

        }

}



//Comments Made by others



class commentsAdapterOthers extends RecyclerView.Adapter<commentsAdapterOthers.commentViewHolderOthers> {
List<String>comments,users;
    public class commentViewHolderOthers extends RecyclerView.ViewHolder {
TextView  commentOthers,username;
        public commentViewHolderOthers(View itemView){
            super(itemView);
            commentOthers=(TextView)itemView.findViewById(R.id.textView_others_comment);
            username=(TextView)itemView.findViewById(R.id.user);
        }
    }
    public commentsAdapterOthers(List<String>comments,List<String>users){
        this.comments=comments;
        this.users=users;
    }
     @Override
    public commentViewHolderOthers onCreateViewHolder(ViewGroup parent, int viewType) {
         View view;
         //  Comments commentsObj=new Comments();
         //!new Comments().currentuser.equals(users.get(viewType))
         if(viewType%2==0) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comments, parent, false);
         }
         else {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mycomment, parent, false);
         }
         return new commentViewHolderOthers(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(commentViewHolderOthers holder, int position) {
        holder.commentOthers.setText(comments.get(position));
        holder.username.setText(users.get(position));
        if(position%2!=0) {

            holder.username.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

}



