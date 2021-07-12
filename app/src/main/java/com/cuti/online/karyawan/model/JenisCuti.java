package com.cuti.online.karyawan.model;

public class JenisCuti {
    public JenisCuti() {
    }

    @Override
    public String toString() {
        return "JenisCuti{" +
                "nama='" + nama + '\'' +
                '}';
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    String nama;
}
