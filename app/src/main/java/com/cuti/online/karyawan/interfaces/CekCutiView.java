package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.Cuti;

import java.util.ArrayList;

public interface CekCutiView {
    void onSuccess(ArrayList<Cuti> cutiArrayList);

    void onFailed(String message);
}
