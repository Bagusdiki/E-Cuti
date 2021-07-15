package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.NotifView;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.presenter.NotifikasiPresenter;
import com.cuti.online.karyawan.ui.adapter.NotifikasiAdapter;
import com.cuti.online.karyawan.utils.Sharedpreferences;

import java.util.ArrayList;

public class NotifikasiActivity extends AppCompatActivity implements NotifView {
    NotifikasiPresenter presenter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        rv = findViewById(R.id.rvNotif);
        initPresenter();
    }

    private void initPresenter() {
        presenter = new NotifikasiPresenter(this);
        presenter.getCekCuti(Sharedpreferences.getString("uid"));
    }

    @Override
    public void onSuccess(ArrayList<Cuti> cutiArrayList) {
        NotifikasiAdapter adapter = new NotifikasiAdapter(this,cutiArrayList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    public void onFailed(String silahkan_ajukan_cuti_terlebih_dahulu) {
        Toast.makeText(getApplicationContext(),"Tidak ada notifikasi",Toast.LENGTH_SHORT).show();
    }
}