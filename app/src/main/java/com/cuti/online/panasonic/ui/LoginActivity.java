package com.cuti.online.panasonic.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuti.online.panasonic.R;
import com.cuti.online.panasonic.interfaces.LoginView;
import com.cuti.online.panasonic.model.User;
import com.cuti.online.panasonic.presenter.LoginPresenter;
import com.cuti.online.panasonic.utils.Sharedpreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements LoginView {
    Button login, register;
    EditText username, password;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initPresenter();
        login = findViewById(R.id.btLogin);
        register = findViewById(R.id.btRegister);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("Tidak boleh kosong");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Tidak boleh kosong");
                } else {
                    login(username.getText().toString(), password.getText().toString());
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

    private void login(String user, String pwd) {
        presenter.login(user, pwd);
    }

    @Override
    public void onSuccess(User user) {
        Sharedpreferences.saveBoolean("isLogin", true);
        Sharedpreferences.saveString("username", user.getUsername());
        Sharedpreferences.saveString("password", user.getPassword());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailed(String password_salah) {
        Toast.makeText(getApplicationContext(), password_salah, Toast.LENGTH_SHORT).show();
    }
}