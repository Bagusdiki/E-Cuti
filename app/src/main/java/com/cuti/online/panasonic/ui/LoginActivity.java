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
import com.cuti.online.panasonic.model.User;
import com.cuti.online.panasonic.utils.Sharedpreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    Button login, register;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                    firebaseLogin();
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

    private void firebaseLogin() {
        FirebaseDatabase.getInstance().getReference().child("Users/" + username.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    if (password.getText().toString().equals(user.getPassword())) {
                        Sharedpreferences.saveBoolean("isLogin", true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Password salah", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User tidak terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Silahkan coba kembali", Toast.LENGTH_SHORT).show();
            }
        });
    }
}