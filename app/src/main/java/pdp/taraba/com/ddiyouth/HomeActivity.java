package pdp.taraba.com.ddiyouth;

/**
 * Created by 5ran6 on 24/10/17.
 */

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    //  private SharedPreferences mSharedPreferences;
    Class fragmentClass = Home.class;
    Fragment myFragment = (Fragment) fragmentClass.newInstance();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    public HomeActivity() throws IllegalAccessException, InstantiationException {
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("What do you want to do?")
                .setCancelable(false)
                .setPositiveButton("Close app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("CONTINUE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            //       myFragment = getSupportFragmentManager().getFragment(savedInstanceState, myFragment.toString());

        }


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nv);
        frameLayout = (FrameLayout) findViewById(R.id.flcontent);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigationView);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
//        savedInstanceState.putString("fragClassName", fragmentClass.toString());
//        final Fragment fragmentInFrame = getSupportFragmentManager().findFragmentById(R.id.flcontent);
//        getSupportFragmentManager().putFragment(outState, fragmentInFrame.getTag(), myFragment);
////        getSupportFragmentManager().putFragment(outState, "myFragmentName", myFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
// When orientation is changed, activity goes through onResume()
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        String fragClassName = savedInstanceState.getString("fragClassName", "Home");
//        try {
//            Class frag = Class.forName(fragClassName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            myFragment = (Fragment) frag.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void selectItemDrawer(MenuItem menuItem) {
        myFragment = null;
        switch (menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = Home.class;
                break;
            case R.id.nycn:
                fragmentClass = NYCN.class;
                break;
            case R.id.ddi:
                fragmentClass = DDI.class;
                break;
            case R.id.deputy:
                fragmentClass = Deputy.class;
                break;
            case R.id.news:
                fragmentClass = News.class;
                break;
            case R.id.contact:
                fragmentClass = Contact.class;
                break;
            case R.id.exit:
                finishAffinity();
                break;
            default:
                // fragmentClass = HomeActivity.class;
        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getItemId());
        mDrawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cont(View view) {
        Intent g = new Intent(getApplicationContext(), Gallery.class);
        Toast.makeText(getApplicationContext(), "For now we shall open this. No contents for the original page", Toast.LENGTH_LONG).show();
        startActivity(g);
    }

    public void gallery(View view) {
        Intent g = new Intent(getApplicationContext(), Gallery.class);
        startActivity(g);
    }
}
