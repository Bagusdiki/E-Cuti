package com.cuti.online.karyawan.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.DataKaryawanView;
import com.cuti.online.karyawan.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class DataKaryawanPresenter {
    DataKaryawanView view;

    public DataKaryawanPresenter(DataKaryawanView view) {
        this.view = view;
    }

    public void getDataKaryawan(String nama) {
        Query reference = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("nama").startAt(nama).endAt(nama+"\uf8ff");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<User> listUser = new ArrayList<>();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        User user = data.getValue(User.class);
                        listUser.add(user);
                        Log.d("KARYAWAN", data.toString());
                    }
                    view.onSuccess(listUser);

                } else {
                    Log.d("KARYAWAN", "DATA KOSONG");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }
}
