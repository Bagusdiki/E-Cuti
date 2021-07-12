package com.cuti.online.panasonic.interfaces;

import com.cuti.online.panasonic.model.User;

public interface ProfileView {
    void onSuccess(User user);

    void onFailed(String error);

    void onEditSuccess();

    void onEditFailed(String error);
}
