package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.LoginAdminView;
import com.cuti.online.karyawan.presenter.LoginAdminPresenter;
import com.cuti.online.karyawan.utils.Sharedpreferences;

public class LoginAdminActivity extends AppCompatActivity implements LoginAdminView {
    LoginAdminPresenter presenter;
    EditText email, password;
    Button signIn;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        initPresenter();
        presenter.checkLogin();
        email = findViewById(R.id.etEmailAdmin);
        password = findViewById(R.id.etPassworAdmin);
        signIn = findViewById(R.id.btLoginAdmin);
        forgotPassword = findViewById(R.id.tvForgotPasswordAdmin);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginAdminActivity.this, ForgotPasswordActivity.class));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    email.setError("Tidak boleh kosong");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Tidak boleh kosong");
                } else {
                    presenter.login(email.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void initPresenter() {
        presenter = new LoginAdminPresenter(this);
    }

    @Override
    public void onSuccess(String task) {
        Sharedpreferences.saveString("uidAdmin", task);
        startActivity(new Intent(LoginAdminActivity.this, AdminMainActivity.class));
        finish();
    }

    @Override
    public void onFailed(String login_gagal) {
        Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
    }
}