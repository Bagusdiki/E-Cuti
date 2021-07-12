package com.cuti.online.panasonic.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cuti.online.panasonic.interfaces.LoginView;
import com.cuti.online.panasonic.model.User;
import com.cuti.online.panasonic.ui.LoginActivity;
import com.cuti.online.panasonic.ui.MainActivity;
import com.cuti.online.panasonic.utils.Sharedpreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPresenter {
    LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void login(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    view.onSuccess(task);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailed("Login Gagal");
                Log.e("Loginfailed:", String.valueOf(e));
            }
        });
    }
}
