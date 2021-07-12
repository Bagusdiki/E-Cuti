package com.cuti.online.panasonic.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cuti.online.panasonic.R;

import java.util.ArrayList;
import java.util.List;

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