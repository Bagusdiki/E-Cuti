package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cuti.online.karyawan.R;

import java.util.ArrayList;

public class PengajuanCutiActivity extends AppCompatActivity {
    Spinner spCuti;
    EditText noTelp, nama, tglMulai, tglSelesai;
    Button simpan;
    ArrayList<String> jenisCuti = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_cuti);
    }
}