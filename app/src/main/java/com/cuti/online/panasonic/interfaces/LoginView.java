package com.cuti.online.panasonic.interfaces;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface LoginView {
     void onSuccess(Task<AuthResult> task);

    void onFailed(String error);
}
