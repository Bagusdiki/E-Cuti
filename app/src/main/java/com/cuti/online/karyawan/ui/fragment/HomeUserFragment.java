package com.cuti.online.karyawan.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.HomeView;
import com.cuti.online.karyawan.model.User;
import com.cuti.online.karyawan.presenter.HomePresenter;
import com.cuti.online.karyawan.ui.CekCutiActivity;
import com.cuti.online.karyawan.ui.NotifikasiActivity;
import com.cuti.online.karyawan.ui.PengajuanCutiActivity;
import com.cuti.online.karyawan.ui.SyaratCutiActivity;
import com.cuti.online.karyawan.utils.Sharedpreferences;

public class HomeUserFragment extends Fragment implements HomeView {
    HomePresenter presenter;
    View root;
    TextView nama;
    private Context mContext;
    SwipeRefreshLayout swipe;
    ImageView notif;
    LinearLayout syaratCuti, pengajuanCuti, cekPengajuan, cekSisaCuti;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home_user, container, false);
        nama = root.findViewById(R.id.tvNamaKaryawan);
        swipe = root.findViewById(R.id.swipeHome);
        notif = root.findViewById(R.id.imgNotif);
        pengajuanCuti = root.findViewById(R.id.layoutPengajuanCuti);
        syaratCuti = root.findViewById(R.id.layoutSyaratCuti);
        cekPengajuan = root.findViewById(R.id.layoutCekPengajuan);
        cekPengajuan.setOnClickListener(view -> {
            startActivity(new Intent(mContext, CekCutiActivity.class));
        });
        pengajuanCuti.setOnClickListener(view -> startActivity(new Intent(mContext, PengajuanCutiActivity.class)));
        syaratCuti.setOnClickListener(view -> startActivity(new Intent(mContext, SyaratCutiActivity.class)));
        notif.setOnClickListener(view -> startActivity(new Intent(mContext, NotifikasiActivity.class)));
        swipe.setOnRefreshListener(() -> initPresenter());
        initPresenter();
        return root;
    }

    private void initPresenter() {
        presenter = new HomePresenter(this);
        presenter.getProfile(Sharedpreferences.getString("uid"));
    }

    @Override
    public void onSuccess(User user) {
        nama.setText(user.getNama());
        swipe.setRefreshing(false);
    }

    @Override
    public void onFailed(String error) {
        swipe.setRefreshing(false);
        Toast.makeText(mContext, "Tidak dapat memuat data\nSilahkan coba kembali", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}