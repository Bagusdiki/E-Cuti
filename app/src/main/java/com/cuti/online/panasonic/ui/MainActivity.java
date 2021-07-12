package com.cuti.online.panasonic.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.cuti.online.panasonic.R;
import com.cuti.online.panasonic.ui.adapter.VPMainAdapter;
import com.cuti.online.panasonic.ui.fragment.HomeUserFragment;
import com.cuti.online.panasonic.ui.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

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
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
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