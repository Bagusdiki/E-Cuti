package com.cuti.online.karyawan.interfaces;

import com.google.android.gms.tasks.Task;

public interface UbahKatasandiView {
    void onUpdated(Task<Void> task);

    void onFailed(String message);
}
