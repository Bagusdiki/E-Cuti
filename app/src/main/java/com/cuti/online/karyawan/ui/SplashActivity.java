package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.LoginAdminView;
import com.cuti.online.karyawan.interfaces.LoginView;

public class SplashActivity extends AppCompatActivity  {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pilihan();
            }
        }, 3000);
    }

    private void pilihan() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashActivity.this);
        alertDialog.setMessage("Login sebagai")
                .setPositiveButton("Admin", (dialogInterface, i) -> {
                    startActivity(new Intent(SplashActivity.this, LoginAdminActivity.class));
                    finish();
                }).setNegativeButton("Karyawan", (dialogInterface, i) -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        });
        alertDialog.show();
    }
}