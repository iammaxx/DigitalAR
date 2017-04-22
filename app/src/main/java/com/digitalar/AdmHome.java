package com.digitalar;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class AdmHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference mData;
    FirebaseAuth mAuth;
    int viewid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_home);
        viewid=R.id.nav_class;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     mData = FirebaseDatabase.getInstance().getReference();
       mAuth = FirebaseAuth.getInstance();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.adm_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mAuth.signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    List<String>classes = new ArrayList<String>(50);
    List<String>courses = new ArrayList<String>(50);
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        viewid=id;

        if (id == R.id.nav_class) {
            // Handle the camera action
            mData.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                // This method is called once with the initial value and again
                                                // whenever data at this location is updated.
                                                // String value = dataSnapshot.getValue(String.class);
                                                HashMap<String, String> s3 = (HashMap) dataSnapshot.child("class").getValue();
                                                Log.e("dede",s3.keySet().toString());
                                                classes=new ArrayList<String>(50);
                                                classes.addAll(s3.keySet());
                                                //Log.d(TAG, "Value is: " + value);
                                            }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

            ItemFragment i1 = ItemFragment.newInstance(classes,getApplicationContext(),0);
            FragmentTransaction f1 =getFragmentManager().beginTransaction();
            f1.replace(R.id.fra1,i1);
            f1.commit();

        } else if (id == R.id.nav_course)
        {
            mData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    // String value = dataSnapshot.getValue(String.class);
                    HashMap<String, String> s3 = (HashMap) dataSnapshot.child("course").getValue();
                    Log.e("dede",s3.keySet().toString());
                    courses=new ArrayList<String>(50);
                    courses.addAll(s3.keySet());
                    //Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
            ItemFragment i2 = ItemFragment.newInstance(courses,this,1);
            FragmentTransaction f2 =getFragmentManager().beginTransaction();
            f2.replace(R.id.fra1,i2);

            f2.commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    Dialog d1;

public void fab(View view)
{
    d1 = new Dialog(this);
    switch(viewid) {
        case R.id.nav_class :
        d1.setContentView(R.layout.activity_addclass);
            break;
        case R.id.nav_course:
            d1.setContentView(R.layout.addcourse);

    }
    d1.show();

   // mData.child("class").setValue(Name);

}
public void addclass(View view)
{
    ProgressDialog p1 = new ProgressDialog(this);
    p1.setMessage("Adding Class ...");
    TextView t1 = (TextView)d1.findViewById(R.id.editTextx);
    p1.show();
    mData.child("class").child(t1.getText().toString()).setValue(t1.getText().toString());
    p1.dismiss();
    d1.dismiss();
}
    public void addcourse(View view)
    {
        ProgressDialog p1 = new ProgressDialog(this);
        p1.setMessage("Adding Course ...");
        TextView t1 = (TextView)d1.findViewById(R.id.editTextx);
        p1.show();
        mData.child("course").child(t1.getText().toString()).setValue(t1.getText().toString());
        p1.dismiss();
        d1.dismiss();
    }
}
