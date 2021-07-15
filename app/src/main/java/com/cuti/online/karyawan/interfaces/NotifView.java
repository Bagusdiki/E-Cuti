package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.Cuti;

import java.util.ArrayList;

public interface NotifView {

    void onSuccess(ArrayList<Cuti> cutiArrayList);

    void onFailed(String silahkan_ajukan_cuti_terlebih_dahulu);
}
