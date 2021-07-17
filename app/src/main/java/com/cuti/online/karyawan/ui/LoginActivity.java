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
import com.cuti.online.karyawan.interfaces.LoginView;
import com.cuti.online.karyawan.presenter.LoginPresenter;
import com.cuti.online.karyawan.utils.Sharedpreferences;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity implements LoginView {
    Button login;
    EditText email, password;
    LoginPresenter presenter;
    TextView register, forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initPresenter();
        presenter.checkLogin();
        login = findViewById(R.id.btLogin);
        register = findViewById(R.id.btRegister);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        forgotPassword = findViewById(R.id.tvForgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    email.setError("Tidak boleh kosong");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Tidak boleh kosong");
                } else {
                    login(email.getText().toString(), password.getText().toString());
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    private void login(String email, String pwd) {
        presenter.login(email, pwd);
    }

    @Override
    public void onSuccess(AuthResult task) {
        Sharedpreferences.saveString("email", task.getUser().getEmail());
        Sharedpreferences.saveString("uid", task.getUser().getUid());
        Sharedpreferences.saveString("password", password.getText().toString());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidasiSuccess(String uid) {
        Sharedpreferences.saveString("uid", uid);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}