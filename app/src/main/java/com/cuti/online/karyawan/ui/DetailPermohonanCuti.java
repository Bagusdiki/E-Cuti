package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.DetailPemohonCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.presenter.DetailPemohonCutiPresenter;

public class DetailPermohonanCuti extends AppCompatActivity implements DetailPemohonCutiView {
    DetailPemohonCutiPresenter presenter;
    TextView nama, mulai, selesai, perihal, noTelp;
    Button setujui, tolak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_permohonan_cuti);
        nama = findViewById(R.id.tvNamaPemohonDetail);
        mulai = findViewById(R.id.tvTglMulaiPemohonDetail);
        selesai = findViewById(R.id.tvTglSelesaiPemohonDetail);
        perihal = findViewById(R.id.tvPerihalPemohonDetail);
        noTelp = findViewById(R.id.tvNoTelpPemohonDetail);
        setujui = findViewById(R.id.btSetujui);
        tolak = findViewById(R.id.btTolak);
        setujui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateCuti(getIntent().getStringExtra("id"), "1");
            }
        });
        tolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateCuti(getIntent().getStringExtra("id"), "-1");

            }
        });
        initPresenter();
    }

    private void initPresenter() {
        presenter = new DetailPemohonCutiPresenter(this);
        presenter.getDetail(getIntent().getStringExtra("id"));
    }

    @Override
    public void onSuccess(Cuti cuti) {
        nama.setText(cuti.getNama());
        mulai.setText(cuti.getMulai());
        selesai.setText(cuti.getSelesai());
        perihal.setText(cuti.getJenisCuti());
        noTelp.setText(cuti.getNoTelpon());
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate() {
        Toast.makeText(getApplicationContext(), "Berhasil di update", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onUpdateFailed(String toString) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();

    }
}