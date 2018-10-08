package com.example.a123.ticktickandroidapp;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TaskFragment.OnFragmentInteractionListener,CalendarFragment.OnFragmentInteractionListener,SettingsFragment.OnFragmentInteractionListener {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private TaskFragment tasksFragment;
    private SettingsFragment settingsFragment;
    private CalendarFragment calendarFragment;
    private FloatingActionButton fab;
    private ListView listView;


    public void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.syncState();

    }

    public void setSimpleToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


    }

    public void hideFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
    }

    public void showFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.show();
    }

    public void hideToggle() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
    }

    public void showToggle() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
    }

//    public void add(View view){
//
//        EditText taskEditText = (EditText) findViewById(R.id.enterTask);
//        String task = taskEditText.getText().toString();
//        if(!task.isEmpty() && tasks.contains(task)==false){
//            adapter.add(task);
//            taskEditText.setText("");
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    public void remove(View view){
//        // получаем и удаляем выделенные элементы
//        for(int i=0; i< selectedTask.size();i++){
//            adapter.remove(selectedTask.get(i));
//        }
//        // снимаем все ранее установленные отметки
//        taskList.clearChoices();
//        // очищаем массив выбраных объектов
//        selectedTask.clear();
//
//        adapter.notifyDataSetChanged();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        RelativeLayout editTextLayout = (RelativeLayout) findViewById(R.id.support_edit_text);
        editTextLayout.setVisibility(View.INVISIBLE);
        tasksFragment = new TaskFragment();
        settingsFragment = new SettingsFragment();
        calendarFragment = new CalendarFragment();
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Add Task!",Toast.LENGTH_SHORT).show();
                RelativeLayout editTextLayout = (RelativeLayout) findViewById(R.id.support_edit_text);
                if(editTextLayout.getVisibility() == View.VISIBLE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    editTextLayout.setVisibility(View.INVISIBLE);
                    mMainNav.setVisibility(View.VISIBLE);
                    view.clearFocus();

                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    editTextLayout.setVisibility(View.VISIBLE);
                    //fab.hide();
                    mMainNav.setVisibility(View.INVISIBLE);
                }
            }
        });


        mMainFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout editTextLayout = (RelativeLayout) findViewById(R.id.support_edit_text);
                if(editTextLayout.getVisibility() == View.VISIBLE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    editTextLayout.setVisibility(View.INVISIBLE);
                    fab.show();
                    mMainNav.setVisibility(View.VISIBLE);
                }
            }
        });

        setToolbar();
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tasks_item:
                        setFragment(tasksFragment);
                        return true;
                    case R.id.calendar_item:
                        setFragment(calendarFragment);
                        return true;
                    case R.id.settings_item:
                        setFragment(settingsFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragment(tasksFragment);

        // обработка установки и снятия отметки в списке

    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }



    private void setFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        RelativeLayout editTextLayout = (RelativeLayout) findViewById(R.id.support_edit_text);
        if(editTextLayout.getVisibility() == View.VISIBLE) {
            showFloatingActionButton();
            editTextLayout.setVisibility(View.INVISIBLE);
            mMainNav.setVisibility(View.VISIBLE);
            return;
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //KeyboardView keyboardView = findViewById(R.id);

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
