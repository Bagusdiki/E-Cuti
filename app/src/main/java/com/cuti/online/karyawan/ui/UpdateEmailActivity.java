package com.cuti.online.karyawan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cuti.online.karyawan.R;
import com.cuti.online.karyawan.interfaces.UpdateEmailView;
import com.cuti.online.karyawan.presenter.UpdateEmailPresenter;
import com.cuti.online.karyawan.utils.Sharedpreferences;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UpdateEmailActivity extends AppCompatActivity implements UpdateEmailView {
    UpdateEmailPresenter presenter;
    EditText oldEmail, newEmail;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        initPresenter();
        oldEmail = findViewById(R.id.etEmailTerdaftar);
        oldEmail.setText(Sharedpreferences.getString("email"));
        newEmail = findViewById(R.id.etEmailBaru);
        simpan = findViewById(R.id.btUpdateEmail);
        simpan.setOnClickListener(view -> {
            if (newEmail.getText().toString().isEmpty()) {
                newEmail.setError("Tidak boleh kosong");
            } else if (newEmail.getText().toString().equals(oldEmail.getText().toString())) {
                newEmail.setError("Tidak boleh sama");
            } else {
                presenter.updateEmail(oldEmail.getText().toString(), newEmail.getText().toString(), Sharedpreferences.getString("password"));
            }
        });
    }

    private void initPresenter() {
        presenter = new UpdateEmailPresenter(this);
    }

    @Override
    public void onUpdated(Task<Void> task) {
        Toast.makeText(getApplicationContext(), "Email berhasil diperbarui", Toast.LENGTH_SHORT).show();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getApplicationContext(), "Email gagal diperbarui", Toast.LENGTH_SHORT).show();
    }
}