package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.User;

public interface HomeView {
    void onSuccess(User user);

    void onFailed(String error);
}
