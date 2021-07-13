package com.cuti.online.karyawan.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.CekCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class CekCutiPresenter {
    CekCutiView view;

    public CekCutiPresenter(CekCutiView view) {
        this.view = view;
    }

    public void getCekCuti(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Cuti/" + uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Cuti> cutiArrayList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Cuti cuti = data.getValue(Cuti.class);
                    cutiArrayList.add(cuti);
                }
                view.onSuccess(cutiArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }
}
