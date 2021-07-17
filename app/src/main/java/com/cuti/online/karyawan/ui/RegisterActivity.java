package com.cuti.online.karyawan.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.RegisterView;
import com.cuti.online.karyawan.presenter.RegisterPresenter;
import com.cuti.online.karyawan.utils.ImageRotator;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    Button register;
    EditText nama, email, password, confirmPassword, noTelpon;
    RegisterPresenter presenter;
    TextView login;
    FloatingActionButton fabFoto;
    CircleImageView foto;
    Bitmap resultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initPresenter();
        nama = findViewById(R.id.etNama);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        noTelpon = findViewById(R.id.etNoTelpon);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        register = findViewById(R.id.btRegister);
        login = findViewById(R.id.tvLogin);
        fabFoto = findViewById(R.id.fabUploadImage);
        foto = findViewById(R.id.imgRegister);
        resultImage = BitmapFactory.decodeResource(getApplication().getResources(),
                R.drawable.account);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().isEmpty()) {
                    nama.setError("Tidak boleh kosong");
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Tidak boleh kosong");

                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Tidak boleh kosong");

                } else if (confirmPassword.getText().toString().isEmpty()) {
                    confirmPassword.setError("Tidak boleh kosong");

                } else if (noTelpon.getText().toString().isEmpty()) {
                    noTelpon.setError("Tidak boleh kosong");
                } else {
                    if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                        confirmPassword.setError("Password tidak sama");
                    } else if (confirmPassword.getText().toString().length() < 6) {
                        confirmPassword.setError("Minimum 6 karakter");
                        password.setError("Minimum 6 karakter");
                    } else {
                        registerFirebase(nama.getText().toString(), noTelpon.getText().toString(), email.getText().toString(), password.getText().toString());
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
        fabFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 0);
            }
        });
    }

    private void initPresenter() {
        presenter = new RegisterPresenter(this);
    }

    private void registerFirebase(String nama, String noTelpon, String email, String password) {
        presenter.register(nama, noTelpon, email, password, resultImage);
    }

    @Override
    public void onSuccess(Task<Void> task) {
        Toast.makeText(getApplicationContext(), "Registrasi berhasil\nSilahkan login", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    Uri uri = data.getData();
                    InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Bitmap fixrotate = ImageRotator.rotateImageIfRequired(bitmap, getApplicationContext(), uri);
                    resultImage = fixrotate;
                    foto.setImageBitmap(fixrotate);
                } catch (FileNotFoundException e) {
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}