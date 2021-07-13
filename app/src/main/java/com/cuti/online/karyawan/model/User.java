package com.cuti.online.karyawan.model;

public class User {
    String nama;
    String email;
    String password;
    String noTelp;
    String foto;
    int sisaCuti;

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getSisaCuti() {
        return sisaCuti;
    }

    public void setSisaCuti(int sisaCuti) {
        this.sisaCuti = sisaCuti;
    }
}
