package com.cuti.online.karyawan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.AdminMainView;
import com.cuti.online.karyawan.model.Admin;
import com.cuti.online.karyawan.presenter.AdminMainPresenter;
import com.cuti.online.karyawan.utils.Sharedpreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminMainActivity extends AppCompatActivity implements AdminMainView {
    BottomNavigationView nav;
    AdminMainPresenter presenter;
    TextView nama;
    LinearLayout layoutDataKaryawan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        initPresenter();
        nama = findViewById(R.id.tvNamaAdmin);
        nav = findViewById(R.id.navAdminMain);
        layoutDataKaryawan = findViewById(R.id.layoutDataKaryawan);
        layoutDataKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this,DataKaryawanActivity.class));
            }
        });
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_keluar_admin) {
                    presenter.logout();
                    return true;
                }
                return false;
            }
        });
    }

    private void initPresenter() {
        presenter = new AdminMainPresenter(this);
        presenter.getUser(Sharedpreferences.getString("uidAdmin"));
    }

    @Override
    public void onLogout() {
        startActivity(new Intent(AdminMainActivity.this, SplashActivity.class));
        Sharedpreferences.edit().remove("uidAdmin").commit();
        finish();
    }

    @Override
    public void onSuccess(Admin admin) {
        nama.setText(admin.getNama().toString());
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
    }
}