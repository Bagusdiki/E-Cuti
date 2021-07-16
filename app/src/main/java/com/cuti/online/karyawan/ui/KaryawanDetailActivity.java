package com.cuti.online.karyawan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cuti.online.karyawan.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class KaryawanDetailActivity extends AppCompatActivity {
    CircleImageView img;
    TextView nama, email, noHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan_detail);
        img = findViewById(R.id.imgKaryawanDetail);
        nama = findViewById(R.id.tvNamaKaryawanDetail);
        email = findViewById(R.id.tvEmailKaryawanDetail);
        noHp = findViewById(R.id.tvNoHpKaryawanDetail);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("foto")).into(img);
        nama.setText(getIntent().getStringExtra("nama"));
        email.setText(getIntent().getStringExtra("email"));
        noHp.setText(getIntent().getStringExtra("noTelp"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }
}