package com.abhishekjagushte.careerblog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.abhishekjagushte.careerblog.dummy.DummyContent;
import com.abhishekjagushte.careerblog.post.PostContent.Post;

public class MainActivity extends AppCompatActivity implements PostFragment.OnListFragmentInteractionListener {

    private DrawerLayout drawer;
    public static Handler handler;
    public static boolean postPageOpen = false;
    Fragment post_fragment;


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
            post_fragment = new PostFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,post_fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(postPageOpen) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, post_fragment).commit();
                postPageOpen=false;
        }else
            super.onBackPressed();

    }

    @Override
    public void onListFragmentInteraction(Post post) {
        Fragment post_page = new PostPage(post);
        postPageOpen=true;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,post_page).commit();
    }
}
