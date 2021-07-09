package com.cuti.online.panasonic.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cuti.online.panasonic.R;
import com.cuti.online.panasonic.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    Button register, login;
    EditText nama, username, password, confirmPassword;
    RadioGroup rbGroup;
    String status;
    DatabaseReference dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = findViewById(R.id.etNama);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        register = findViewById(R.id.btRegister);
        login = findViewById(R.id.btLogin);
        rbGroup = findViewById(R.id.rbGroup);
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbAdmin:
                        status = "ADMIN";
                        break;
                    case R.id.rbKaryawan:
                        status = "KARYAWAN";
                        break;
                    default:
                        status = null;
                        break;
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().isEmpty()) {
                    nama.setError("Tidak boleh kosong");
                } else if (username.getText().toString().isEmpty()) {
                    username.setError("Tidak boleh kosong");

                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Tidak boleh kosong");

                } else if (confirmPassword.getText().toString().isEmpty()) {
                    confirmPassword.setError("Tidak boleh kosong");

                } else {
                    if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                        registerFirebase();
                    } else {
                        confirmPassword.setError("Password tidak sama");

                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void registerFirebase() {
        dbUser = FirebaseDatabase.getInstance().getReference().child("Users" + "/" + username.getText().toString());
        dbUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user == null) {
                    pushToDatabase();
                } else {
                    Toast.makeText(getApplicationContext(), "Username sudah terdaftar silahkan login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void pushToDatabase() {
        User user = new User();
        user.setNama(nama.getText().toString());
        user.setStatus(status);
        user.setPassword(password.getText().toString());
        user.setUsername(username.getText().toString());
        dbUser.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Registrasi berhasil\nSilahkan login", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Registrasi Gagal\nCoba kembali", Toast.LENGTH_SHORT).show();
            }
        });
    }
}