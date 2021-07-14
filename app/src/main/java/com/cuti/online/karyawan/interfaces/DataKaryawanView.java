package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.User;

import java.util.ArrayList;

public interface DataKaryawanView {
    void onSuccess(ArrayList<User> listUser);

    void onFailed(String message);
}
