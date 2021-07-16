package com.cuti.online.karyawan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.ui.fragment.HomeUserFragment;
import com.cuti.online.karyawan.ui.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(new HomeUserFragment());
        bNav = findViewById(R.id.bNavMain);
        bNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        return openFragment(new HomeUserFragment());
                    case R.id.menu_profile:
                        return openFragment(new ProfileFragment());
                    case R.id.menu_keluar:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        finish();
                }
                return false;
            }
        });
    }

    private Boolean openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameMain, fragment).commit();
        return true;
    }
}