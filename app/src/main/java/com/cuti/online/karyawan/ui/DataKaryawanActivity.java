package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.DataKaryawanView;
import com.cuti.online.karyawan.model.User;
import com.cuti.online.karyawan.presenter.DataKaryawanPresenter;
import com.cuti.online.karyawan.ui.adapter.DataKaryawanAdapter;

import java.util.ArrayList;

public class DataKaryawanActivity extends AppCompatActivity implements DataKaryawanView {
    DataKaryawanPresenter presenter;
    SearchView searchView;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_karyawan);
        initPresenter();
        rv = findViewById(R.id.rvDataKaryawan);
        searchView = findViewById(R.id.searchKaryawan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.getDataKaryawan(newText);
                return true;
            }
        });

    }

    private void initPresenter() {
        presenter = new DataKaryawanPresenter(this);
        presenter.getDataKaryawan("");
    }

    @Override
    public void onSuccess(ArrayList<User> listUser) {
        DataKaryawanAdapter adapter = new DataKaryawanAdapter(listUser, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak dapat memuat data", Toast.LENGTH_SHORT).show();
    }
}