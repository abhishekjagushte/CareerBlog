package com.abhishekjagushte.careerblog;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.widget.Toast;

import com.abhishekjagushte.careerblog.dummy.DummyContent;
import com.abhishekjagushte.careerblog.post.PostContent;
import com.abhishekjagushte.careerblog.post.PostListDecoder;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements PostFragment.OnListFragmentInteractionListener {

    private DrawerLayout drawer;
    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        handler = new Handler(Looper.getMainLooper());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState ==null){
            Fragment fragment = new PostFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onListFragmentInteraction(PostContent.Post post) {

    }
}
