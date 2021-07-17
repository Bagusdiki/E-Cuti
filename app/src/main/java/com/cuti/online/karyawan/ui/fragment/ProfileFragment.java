package com.cuti.online.karyawan.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.ProfileView;
import com.cuti.online.karyawan.model.User;
import com.cuti.online.karyawan.presenter.ProfilePresenter;
import com.cuti.online.karyawan.ui.EditProfileActivity;
import com.cuti.online.karyawan.ui.KebijakanPrivasiActivity;
import com.cuti.online.karyawan.ui.NotifikasiActivity;
import com.cuti.online.karyawan.ui.SyaratKetentuan;
import com.cuti.online.karyawan.ui.UbahKatasandiActivity;
import com.cuti.online.karyawan.ui.UpdateEmailActivity;
import com.cuti.online.karyawan.utils.ImageRotator;
import com.cuti.online.karyawan.utils.Sharedpreferences;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ProfileView {
    ProfilePresenter presenter;
    View root;
    TextView nama, noTelp;
    LinearLayout pwd, profile, syarat, kebijakan;
    CircleImageView img;
    ImageView notif;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_profile, container, false);
        nama = root.findViewById(R.id.tvNamaProfile);
        noTelp = root.findViewById(R.id.tvNoTelpProfile);
        img = root.findViewById(R.id.imgProfile);
        profile = root.findViewById(R.id.layoutUbahProfil);
        profile.setOnClickListener(view -> {
            startActivity(new Intent(mContext, EditProfileActivity.class));

        });
        pwd = root.findViewById(R.id.layoutKataSandi);
        pwd.setOnClickListener(view -> {
            startActivity(new Intent(mContext, UbahKatasandiActivity.class));

        });
        notif = root.findViewById(R.id.imgNotifProfile);
        notif.setOnClickListener(view -> {
            startActivity(new Intent(mContext, NotifikasiActivity.class));
        });
        syarat = root.findViewById(R.id.layoutSyaratKetentuan);
        syarat.setOnClickListener(view -> startActivity(new Intent(mContext, SyaratKetentuan.class)));
        kebijakan = root.findViewById(R.id.layoutKebijakanPrivasi);
        kebijakan.setOnClickListener(view -> startActivity(new Intent(mContext, KebijakanPrivasiActivity.class)));
        initPresenter();
        return root;
    }

    private void initPresenter() {
        presenter = new ProfilePresenter(this);
        presenter.getProfile(Sharedpreferences.getString("uid"));
    }

    private void enable(Boolean status) {
        noTelp.setEnabled(status);
        nama.setEnabled(status);
    }

    @Override
    public void onSuccess(User user) {
        nama.setText(user.getNama());
        noTelp.setText(user.getNoTelp());
        Glide.with(mContext).load(user.getFoto()).into(img);

    }

    @Override
    public void onFailed(String error) {
        enable(false);
        Toast.makeText(mContext, "Gagal memuat profile", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSuccess() {
        Toast.makeText(mContext, "Berhasil memperbarui profile", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEditFailed(String error) {
        Toast.makeText(mContext, "Gagal memperbarui profile", Toast.LENGTH_SHORT).show();
        enable(false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}