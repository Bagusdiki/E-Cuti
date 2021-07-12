package com.cuti.online.karyawan.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void login(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    view.onSuccess(task);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailed("Login Gagal");
                Log.e("Loginfailed:", String.valueOf(e));
            }
        });
    }
}
