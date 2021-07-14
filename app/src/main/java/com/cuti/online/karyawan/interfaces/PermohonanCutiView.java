package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.Cuti;

import java.util.ArrayList;

public interface PermohonanCutiView {

    void onSuccess(ArrayList<Cuti> cutiArrayList, ArrayList<String> key);

    void onFailed(String message);
}
