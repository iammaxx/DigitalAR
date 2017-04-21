package com.digitalar;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        viewid=id;

        if (id == R.id.nav_class) {
            // Handle the camera action
            ItemFragment i1 = ItemFragment.newInstance(2);
            FragmentTransaction f1 =getFragmentManager().beginTransaction();
            f1.add(R.id.fra1,i1);
            f1.commit();

        } else if (id == R.id.nav_course)
        {

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
