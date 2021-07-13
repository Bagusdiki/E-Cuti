package com.cuti.online.karyawan.model;

public class Admin {
    String nama;
    String email;

    public Admin() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
