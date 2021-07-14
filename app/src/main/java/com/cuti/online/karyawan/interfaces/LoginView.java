package com.cuti.online.karyawan.interfaces;

import com.google.firebase.auth.AuthResult;

public interface LoginView {
     void onSuccess(AuthResult task);

    void onFailed(String error);

    void onValidasiSuccess(String uid);
}
