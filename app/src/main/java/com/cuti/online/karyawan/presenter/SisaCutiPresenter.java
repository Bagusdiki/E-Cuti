package com.cuti.online.karyawan.presenter;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.SisaCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SisaCutiPresenter {
    SisaCutiView view;

    public SisaCutiPresenter(SisaCutiView view) {
        this.view = view;
    }

    public void getCekCuti(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Cuti/" + uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<Cuti> cutiArrayList = new ArrayList<>();
                    Cuti cuti = snapshot.getValue(Cuti.class);
                    if (String.valueOf(cuti.getStatus()).equals("1")) {
                        cutiArrayList.add(cuti);
                    }
                    view.onSuccess(cutiArrayList);
                } else {
                    view.onFailed("Silahkan ajukan cuti terlebih dahulu");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }
}
