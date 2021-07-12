package com.cuti.online.panasonic.interfaces;

import com.google.android.gms.tasks.Task;
public interface RegisterView {
    void onSuccess(Task<Void> task);

    void onFailed(String s);
}
