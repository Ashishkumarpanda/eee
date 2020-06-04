package com.example.lotus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.example.lotus.AcademicFragment;
import com.example.lotus.HomeFragment;
import com.example.lotus.LibraryFineCalculatorFragment;
import com.example.lotus.SyllabusFragment;
import com.example.lotus.TimeTableFragment;
import com.example.lotus.ToDoFragment;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        container = findViewById(R.id.frame_container);
        getSupportFragmentManager().beginTransaction().replace(container.getId(),
                new HomeFragment()).commit();
        setTitle("Important Apps");
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        TextView usermail = navigationView.getHeaderView(0).findViewById(R.id.user_mail);
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            usermail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }



        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            switch (item.getItemId()) {
                case R.id.menu_imp_apps:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(),
                            new HomeFragment()).commit();
                    setTitle("Important Apps");
                    break;
                case R.id.menu_timetable:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(),
                            new TimeTableFragment()).commit();
                    setTitle("Time Table");
                    break;
                case R.id.menu_syllabus:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(),
                            new SyllabusFragment()).commit();
                    setTitle("Syllabus");
                    break;
                case R.id.menu_academic:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(),
                            new AcademicFragment()).commit();
                    setTitle("Academic Section");
                    break;
                case R.id.menu_library_fine_calc:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(),
                            new LibraryFineCalculatorFragment()).commit();
                    setTitle("Library Fine Calculator");
                    break;
                case R.id.menu_todo:
                    getSupportFragmentManager().beginTransaction().replace(container.getId(),
                            new ToDoFragment()).commit();
                    setTitle("To-Do List");
                    break;
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, Login.class));
                    finish();
            }
            drawerLayout.closeDrawers();
            return true;
        }
    }
