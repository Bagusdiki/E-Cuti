package com.cuti.online.karyawan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.PermohonanCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.cuti.online.karyawan.presenter.PermohonanCutiPresenter;
import com.cuti.online.karyawan.ui.adapter.PermohonanCutiAdapter;

import java.util.ArrayList;

public class PermohonanCutiActivity extends AppCompatActivity implements PermohonanCutiView {
    PermohonanCutiPresenter presenter;
    RecyclerView rv;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permohonan_cuti);
        initPresenter();
        rv = findViewById(R.id.rvPermohonanCutiAdmin);
        searchView = findViewById(R.id.searchPermohonan);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.getPermohonan(newText);
                return true;
            }
        });
    }

    private void initPresenter() {
        presenter = new PermohonanCutiPresenter(this);
        presenter.getPermohonan("");
    }

    @Override
    public void onSuccess(ArrayList<Cuti> cutiArrayList, ArrayList<String> key) {
        PermohonanCutiAdapter adapter = new PermohonanCutiAdapter(this, cutiArrayList, key);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPresenter();
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