package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.Admin;

public interface AdminMainView {
    void onLogout();

    void onSuccess(Admin admin);

    void onFailed(String message);
}
