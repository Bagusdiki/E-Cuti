package com.cuti.online.panasonic.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.cuti.online.panasonic.R;
import com.cuti.online.panasonic.ui.adapter.VPMainAdapter;
import com.cuti.online.panasonic.ui.fragment.ListCutiFragment;
import com.cuti.online.panasonic.ui.fragment.SisaCutiFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager vP;
    VPMainAdapter vpMainAdapter;
    ArrayList<Fragment> listFragment = new ArrayList<>();
    ArrayList<String> listTitle = new ArrayList<>();
    DatabaseReference dbCuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabMain);
        vP = findViewById(R.id.vpMain);
        listFragment.add(new ListCutiFragment());
        listFragment.add(new SisaCutiFragment());
        listTitle.add("List Cuti");
        listTitle.add("Sisa Cuti");
        vpMainAdapter = new VPMainAdapter(getSupportFragmentManager());
        vpMainAdapter.setList_fragment(listFragment);
        vpMainAdapter.setList_title(listTitle);
        vP.setAdapter(vpMainAdapter);
        tabLayout.setupWithViewPager(vP);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vP.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}