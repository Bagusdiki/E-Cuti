package com.cuti.online.panasonic.interfaces;

import com.cuti.online.panasonic.model.User;

/**
 * Created by Gustu Maulana Firmansyah on 10,July,2021  gustumaulanaf@gmail.com
 **/
public interface LoginView {
    public void onSuccess(User user);

    void onFailed(String password_salah);
}
