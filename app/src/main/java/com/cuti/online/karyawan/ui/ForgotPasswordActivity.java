package com.cuti.online.karyawan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.ForgotPasswordView;
import com.cuti.online.karyawan.presenter.ForgotPasswordPresenter;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordView {
    EditText email;
    Button bt;
    ForgotPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = findViewById(R.id.etEmailForgot);
        bt = findViewById(R.id.btSendForgot);
        initPresenter();
        bt.setOnClickListener(view -> {
            if (email.getText().toString().isEmpty()) {
                email.setError("Tidak boleh kosong");
            } else {
                presenter.sendEmail(email.getText().toString());
            }
        });
    }

    private void initPresenter() {
        presenter = new ForgotPasswordPresenter(this);
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getApplicationContext(), "Berhasil\nSilahkan periksa pesan email Anda", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}