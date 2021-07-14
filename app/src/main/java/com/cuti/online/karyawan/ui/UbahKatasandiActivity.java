package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.UbahKatasandiView;
import com.cuti.online.karyawan.presenter.UbahKataSandiPresenter;
import com.cuti.online.karyawan.utils.Sharedpreferences;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UbahKatasandiActivity extends AppCompatActivity implements UbahKatasandiView {
    EditText oldPwd, newPwd, newPwd2;
    Button simpan;
    UbahKataSandiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_katasandi);
        oldPwd = findViewById(R.id.etPasswordNow);
        newPwd = findViewById(R.id.etNewPassword);
        newPwd2 = findViewById(R.id.etNewPassword2);
        simpan = findViewById(R.id.btUpdatePassword);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oldPwd.getText().toString().isEmpty()) {
                    oldPwd.setError("Tidak boleh kosong");
                } else if (newPwd.getText().toString().isEmpty()) {
                    newPwd.setError("Tidak boleh kosong");
                } else if (newPwd2.getText().toString().isEmpty()) {
                    newPwd2.setError("Tidak boleh kosong");
                } else {
                    if (!oldPwd.getText().toString().equals(Sharedpreferences.getString("password"))) {
                        oldPwd.setError("Password salah");
                    } else if (!newPwd2.getText().toString().equals(newPwd.getText().toString())) {
                        newPwd2.setError("Tidak sama");
                    } else if (newPwd2.getText().toString().equals(oldPwd.getText().toString())) {
                        newPwd2.setError("Tidak boleh sama password lama");
                    } else {
                        presenter.updatePassword(Sharedpreferences.getString("email"), oldPwd.getText().toString(), newPwd2.getText().toString());
                    }
                }
            }
        });
        initPresenter();
    }

    private void initPresenter() {
        presenter = new UbahKataSandiPresenter(this);

    }

    @Override
    public void onUpdated(Task<Void> task) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(), "Berhasil mengubah kata sandi\nSilahkan login kembali", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), "Gagal mengubah kata sandi\nSilahkan coba kembali", Toast.LENGTH_SHORT).show();

    }
}