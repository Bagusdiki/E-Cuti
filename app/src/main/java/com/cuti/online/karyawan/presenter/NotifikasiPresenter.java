package com.cuti.online.karyawan.presenter;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.NotifView;
import com.cuti.online.karyawan.model.Cuti;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotifikasiPresenter {
    NotifView view;

    public NotifikasiPresenter(NotifView view) {
        this.view = view;
    }

    public void getCekCuti(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Cuti/" + uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    ArrayList<Cuti> cutiArrayList = new ArrayList<>();
                    Cuti cuti = snapshot.getValue(Cuti.class);
                    cutiArrayList.add(cuti);
                    view.onSuccess(cutiArrayList);
                }else{
                    view.onFailed("Tidak ada notifikasi");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }
}
