package com.cuti.online.karyawan.interfaces;

import com.cuti.online.karyawan.model.Cuti;

public interface DetailPemohonCutiView {
    void onSuccess(Cuti cuti);

    void onFailed(String message);

    void onUpdate();

    void onUpdateFailed(String toString);
}
