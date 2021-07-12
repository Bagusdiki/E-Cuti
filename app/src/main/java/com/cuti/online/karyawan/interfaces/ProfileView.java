package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.User;

public interface ProfileView {
    void onSuccess(User user);

    void onFailed(String error);

    void onEditSuccess();

    void onEditFailed(String error);
}
