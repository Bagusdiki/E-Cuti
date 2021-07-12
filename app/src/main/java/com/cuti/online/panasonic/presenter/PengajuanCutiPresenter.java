package com.cuti.online.panasonic.presenter;

import androidx.annotation.NonNull;

import com.cuti.online.panasonic.interfaces.PengajuanCutiView;
import com.cuti.online.panasonic.model.Cuti;
import com.cuti.online.panasonic.model.JenisCuti;
import com.cuti.online.panasonic.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class PengajuanCutiPresenter {
    PengajuanCutiView view;

    public PengajuanCutiPresenter(PengajuanCutiView view) {
        this.view = view;
    }

    public void getJenisCuti() {
        FirebaseDatabase.getInstance().getReference().child("JenisCuti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<JenisCuti> listJenisCuti = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    JenisCuti jenisCuti = data.getValue(JenisCuti.class);
                    listJenisCuti.add(jenisCuti);
                }
                view.onGetJenisCutiSuccess(listJenisCuti);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onGetJenisCutiFailed(error.getMessage());
            }
        });
    }

    public void getProfile(String uid) {
        FirebaseDatabase.getInstance().getReference().child("Users/" + uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                view.onGetProfileSuccess(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                view.onGetProfileFailed(error.getMessage());
            }
        });
    }

    public void submitCuti(Cuti cuti) {
        FirebaseDatabase.getInstance().getReference().child("Cuti").push().setValue(cuti).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                view.onSubmitSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onSubmitFailed(e.getMessage());
            }
        });
    }
}
