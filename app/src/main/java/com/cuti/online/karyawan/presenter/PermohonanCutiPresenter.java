package com.cuti.online.karyawan.presenter;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.PermohonanCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PermohonanCutiPresenter {
    PermohonanCutiView view;

    public PermohonanCutiPresenter(PermohonanCutiView view) {
        this.view = view;
    }
    public void getPermohonan(){
        Query query = FirebaseDatabase.getInstance().getReference().child("Cuti");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Cuti> cutiArrayList = new ArrayList<>() ;
                ArrayList<String> key = new ArrayList<>();
                for (DataSnapshot data:snapshot.getChildren()){
                    Cuti cuti = data.getValue(Cuti.class);
                    cutiArrayList.add(cuti);
                    key.add(data.getKey());
                }
                view.onSuccess(cutiArrayList,key);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            view.onFailed(error.getMessage());
            }
        });
    }
}
