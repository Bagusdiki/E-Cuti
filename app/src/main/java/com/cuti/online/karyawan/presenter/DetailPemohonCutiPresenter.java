package com.cuti.online.karyawan.presenter;

import androidx.annotation.NonNull;

import com.cuti.online.karyawan.interfaces.DetailPemohonCutiView;
import com.cuti.online.karyawan.model.Cuti;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailPemohonCutiPresenter {
    DetailPemohonCutiView view;

    public DetailPemohonCutiPresenter(DetailPemohonCutiView view) {
        this.view = view;
    }

    public void getDetail(String id) {
        FirebaseDatabase.getInstance().getReference().child("Cuti/" + id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Cuti cuti = snapshot.getValue(Cuti.class);
                view.onSuccess(cuti);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onFailed(error.getMessage());
            }
        });
    }

    public void updateCuti(String id, String status) {
        Map map = new HashMap<String, String>();
        map.put("status", status);
        FirebaseDatabase.getInstance().getReference().child("Cuti/" + id).updateChildren(map).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    view.onUpdate();
                }
                else {
                    view.onUpdateFailed(task.getResult().toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    view.onUpdateFailed(e.getMessage());
            }
        });
    }
}
