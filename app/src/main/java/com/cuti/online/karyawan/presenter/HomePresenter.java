package com.cuti.online.karyawan.presenter;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.HomeView;
import com.cuti.online.karyawan.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePresenter {
    HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void getProfile(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Users/" + uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                view.onSuccess(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(String.valueOf(error.getMessage()));
            }
        });
    }
}
