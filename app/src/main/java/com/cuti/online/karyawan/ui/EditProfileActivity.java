package com.cuti.online.karyawan.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.ProfileView;
import com.cuti.online.karyawan.model.User;
import com.cuti.online.karyawan.presenter.ProfilePresenter;
import com.cuti.online.karyawan.utils.ImageRotator;
import com.cuti.online.karyawan.utils.Sharedpreferences;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements ProfileView {
    User user = new User();
    ProfilePresenter presenter;
    CircleImageView imgProfile;
    EditText nama, noTelp;
    TextView email, ubah, updateFoto;
    Button simpan, batal;
    Bitmap resultImage;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
        imgProfile = findViewById(R.id.imgProfile);
        nama = findViewById(R.id.etNamaProfile);
        noTelp = findViewById(R.id.etNoTelpProfile);
        email = findViewById(R.id.tvEmailProfile);
        ubah = findViewById(R.id.tvUbah);
        ubah.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), UpdateEmailActivity.class)));
        simpan = findViewById(R.id.btSimpanProfile);
        updateFoto = findViewById(R.id.tvPerbaruiFotoProfile);
        batal = findViewById(R.id.btCancelProfile);
        swipe = findViewById(R.id.swipeProfile);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initPresenter();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (simpan.getText().toString().equals("Simpan")) {
                    user.setNoTelp(noTelp.getText().toString());
                    user.setNama(nama.getText().toString());
                    resultImage = ((BitmapDrawable) imgProfile.getDrawable()).getBitmap();
                    presenter.updateProfile(resultImage, Sharedpreferences.getString("uid"), user);
                } else {
                    enable(true);
                    simpan.setText("Simpan");
                    batal.setVisibility(View.VISIBLE);
                }
            }
        });
        updateFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 0);
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enable(false);
                simpan.setText("Edit Profile");
                batal.setVisibility(View.GONE);

            }
        });
        initPresenter();
    }

    private void initPresenter() {
        presenter = new ProfilePresenter(this);
        presenter.getProfile(Sharedpreferences.getString("uid"));
    }

    private void enable(Boolean status) {
        noTelp.setEnabled(status);
        nama.setEnabled(status);
        updateFoto.setEnabled(status);
    }

    @Override
    public void onSuccess(User user) {
        this.user = user;
        nama.setText(user.getNama());
        noTelp.setText(user.getNoTelp());
        email.setText(user.getEmail());
        Glide.with(getApplicationContext()).load(user.getFoto()).into(imgProfile);
        simpan.setVisibility(View.VISIBLE);
        simpan.setText("Edit Profile");
        enable(false);
        swipe.setRefreshing(false);
    }

    @Override
    public void onFailed(String error) {
        enable(false);
        Toast.makeText(getApplicationContext(), "Gagal memuat profile", Toast.LENGTH_SHORT).show();
        swipe.setRefreshing(false);
    }

    @Override
    public void onEditSuccess() {
        batal.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Berhasil memperbarui profile", Toast.LENGTH_SHORT).show();
        enable(false);
        simpan.setText("Edit Profile");
        swipe.setRefreshing(false);
    }

    @Override
    public void onEditFailed(String error) {
        simpan.setText("Edit Profile");
        batal.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Gagal memperbarui profile", Toast.LENGTH_SHORT).show();
        enable(false);
        swipe.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    Uri uri = data.getData();
                    InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Bitmap fixrotate = ImageRotator.rotateImageIfRequired(bitmap, getApplicationContext(), uri);
                    imgProfile.setImageBitmap(fixrotate);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}