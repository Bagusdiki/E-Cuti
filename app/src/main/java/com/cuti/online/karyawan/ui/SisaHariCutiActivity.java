package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.SisaCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.presenter.SisaCutiPresenter;
import com.cuti.online.karyawan.ui.adapter.SisaCutiAdapter;
import com.cuti.online.karyawan.utils.Sharedpreferences;

import java.util.ArrayList;

public class SisaHariCutiActivity extends AppCompatActivity implements SisaCutiView {
    SisaCutiPresenter presenter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sisa_hari_cuti);
        rv = findViewById(R.id.rvSisaCuti);
        initPresenter();
    }

    private void initPresenter() {
        presenter = new SisaCutiPresenter(this);
        presenter.getCekCuti(Sharedpreferences.getString("uid"));
    }

    @Override
    public void onSuccess(ArrayList<Cuti> cutiArrayList) {
       if (cutiArrayList.size()>0){
           SisaCutiAdapter adapter = new SisaCutiAdapter(this,cutiArrayList);
           rv.setLayoutManager(new LinearLayoutManager(this));
           rv.setHasFixedSize(true);
           rv.setAdapter(adapter);
       }else {
           Toast.makeText(getApplicationContext(),"Silahkan ajukan cuti terlebih dahulu",Toast.LENGTH_SHORT).show();

       }
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}