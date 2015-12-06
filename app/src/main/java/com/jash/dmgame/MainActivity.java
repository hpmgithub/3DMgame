package com.jash.dmgame;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.jash.dmgame.databinding.NavHeaderBinding;
import com.jash.dmgame.fragments.ChapterFragment;
import com.jash.dmgame.fragments.ForumFragment;
import com.jash.dmgame.fragments.GameFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout drawer;
    private ChapterFragment chapter;
    private ForumFragment forum;
    private GameFragment game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavigationView navigation = (NavigationView) findViewById(R.id.main_nav);
        navigation.setNavigationItemSelectedListener(this);
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        chapter = new ChapterFragment();
        forum = new ForumFragment();
        game = new GameFragment();
        transaction.add(R.id.main_container, chapter)
                .add(R.id.main_container, forum)
                .add(R.id.main_container, game)
                .detach(forum)
                .detach(game)
                .detach(chapter)
                .attach(chapter).commit();
        Bitmap bitmap;
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
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.nav_chapter:
                transaction.detach(forum).detach(game).attach(chapter);
                break;
            case R.id.nav_forum:
                transaction.detach(chapter).detach(game).attach(forum);
                break;
            case R.id.nav_game:
                transaction.detach(forum).detach(chapter).attach(game);
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_exit:
                ActivityCompat.finishAffinity(this);
                break;
        }
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @BindingAdapter("bind:header")
    public static void loadHeader(NavigationView view, int id){
        NavHeaderBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), id, view, false);
        view.addHeaderView(inflate.getRoot());
    }
}
