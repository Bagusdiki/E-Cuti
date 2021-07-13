package com.cuti.online.karyawan.presenter;


import android.util.Log;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.AdminMainView;
import com.cuti.online.karyawan.model.Admin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminMainPresenter {
    AdminMainView view;

    public AdminMainPresenter(AdminMainView view) {
        this.view = view;
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        view.onLogout();
    }

    public void getUser(String uidAdmin) {
        FirebaseDatabase.getInstance().getReference().child("Admin/" + uidAdmin).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Admin admin = snapshot.getValue(Admin.class);
                view.onSuccess(admin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }
}
