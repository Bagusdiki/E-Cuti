package com.cuti.online.panasonic.presenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cuti.online.panasonic.interfaces.RegisterView;
import com.cuti.online.panasonic.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Gustu Maulana Firmansyah on 10,July,2021  gustumaulanaf@gmail.com
 **/
public class RegisterPresenter {
    RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    public void register(String nama, String username, String password, String status) {
        FirebaseDatabase.getInstance().getReference().child("Users" + "/" + username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user == null) {
                    pushToDatabase(nama, username, password, status);
                } else {
                    view.onFailed("User sudah terdaftar\nSilahkan Login");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void pushToDatabase(String nama, String username, String password, String status) {
        User user = new User();
        user.setNama(nama);
        user.setStatus(status);
        user.setPassword(password);
        user.setUsername(username);
        FirebaseDatabase.getInstance().getReference().child("Users" + "/" + username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                view.onSuccess(task);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailed("Registrasi gagal\nSilahkan coba kembali");
            }
        });
    }
}
