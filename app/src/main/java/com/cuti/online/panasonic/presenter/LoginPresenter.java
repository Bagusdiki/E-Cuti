package com.cuti.online.panasonic.presenter;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cuti.online.panasonic.interfaces.LoginView;
import com.cuti.online.panasonic.model.User;
import com.cuti.online.panasonic.ui.LoginActivity;
import com.cuti.online.panasonic.ui.MainActivity;
import com.cuti.online.panasonic.utils.Sharedpreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Gustu Maulana Firmansyah on 10,July,2021  gustumaulanaf@gmail.com
 **/
public class LoginPresenter {
    LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void login(String username, String password) {
        FirebaseDatabase.getInstance().getReference().child("Users/" + username).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user != null) {
                            if (password.equals(user.getPassword())) {
                                view.onSuccess(user);
                            } else {
                                view.onFailed("Password salah");
                            }
                        } else {
                            view.onFailed("User tidak terdaftar");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        view.onFailed("Silahkan coba kembali");
                    }
                });

    }
}
