package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.CekCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.presenter.CekCutiPresenter;
import com.cuti.online.karyawan.ui.adapter.CekCutiAdapter;
import com.cuti.online.karyawan.utils.Sharedpreferences;

import java.util.ArrayList;

public class CekCutiActivity extends AppCompatActivity implements CekCutiView {
    CekCutiPresenter presenter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_cuti);
        rv = findViewById(R.id.rvCekCuti);
        initPresenter();
    }

    private void initPresenter() {
        presenter = new CekCutiPresenter(this);
        presenter.getCekCuti(Sharedpreferences.getString("uid"));
    }

    @Override
    public void onSuccess(ArrayList<Cuti> cutiArrayList) {
       if (cutiArrayList.size()>0){
           CekCutiAdapter adapter = new CekCutiAdapter(getApplicationContext(),cutiArrayList);
           rv.setLayoutManager(new LinearLayoutManager(this));
           rv.setHasFixedSize(true);
           rv.setAdapter(adapter);
       }else {
           Toast.makeText(getApplicationContext(),"Silahkan ajukan cuti terlebih dahulu",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

}