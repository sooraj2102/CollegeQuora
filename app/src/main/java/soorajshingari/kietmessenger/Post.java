package soorajshingari.kietmessenger;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Transaction;
import com.google.firebase.auth.FirebaseAuth;

public class Post extends AppCompatActivity {

    private FragmentPagerAdapter mSectionsPagerAdapter;
    boolean doubleBackPress=false;

    private ViewPager mViewPager;
    Fragment fragment;
    FrameLayout frameLayout;
    Class fragmentclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(!NetworkCheck.isNetworkAvailable(Post.this))
        {
            Snackbar.make(findViewById(android.R.id.content),"Check Your Internet Connection!",Snackbar.LENGTH_LONG)
                    .setAction("Retry",null).show();
        }
        mSectionsPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            Fragment[] fragment=new Fragment[]{
                    new MyPost(),
                    new OthersPost(),
                    new MostActive()

            };
            String fragmentname[]=new String[]{
                "My Post","Other's Post","Most Active People"
            };
            @Override
            public Fragment getItem(int position) {
                return fragment[position];
            }

            @Override
            public int getCount() {
                return fragment.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentname[position];
            }
        };




        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                startActivity(new Intent(Post.this,CreatePost.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       /* if (id == R.id.action_settings) {

            startActivity(new Intent(this,Comments.class));
        }
        else if(id==R.id.create_post)
        {
            startActivity(new Intent(Post.this,CreatePost.class));
        }
      */
         if(id==R.id.log_out)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setCancelable(false)
                    .setMessage("Do you want to log out?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(Post.this,Are_you_new.class));
                            finish();
                        }
                    }).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(doubleBackPress)
        {
            super.onBackPressed();
        }
        else
        {
            doubleBackPress=true;
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
              doubleBackPress=false;
             }
         },2000);
            Snackbar.make(findViewById(android.R.id.content),"Press again to exit",Snackbar.LENGTH_LONG).setAction("Action",null).show();
        }

    }
}
