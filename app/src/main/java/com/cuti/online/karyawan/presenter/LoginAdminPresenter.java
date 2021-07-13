package com.cuti.online.karyawan.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.LoginAdminView;
import com.cuti.online.karyawan.model.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdminPresenter {
    LoginAdminView view;

    public LoginAdminPresenter(LoginAdminView view) {
        this.view = view;
    }

    public void login(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    validasi(task.getResult().getUser().getUid());
                }else {
                    view.onFailed("Login gagal");
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

    public void validasi(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Admin/" + uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Admin admin = snapshot.getValue(Admin.class);
                    view.onSuccess(uid);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }

    public Boolean checkLogin() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            validasi(FirebaseAuth.getInstance().getCurrentUser().getUid());
            return true;
        } else {
            return false;
        }
    }
}
