package com.cuti.online.panasonic.interfaces;

import com.cuti.online.panasonic.model.User;

public interface HomeView {
    void onSuccess(User user);

    void onFailed(String error);
}
