package com.cuti.online.panasonic.interfaces;

import com.cuti.online.panasonic.model.JenisCuti;
import com.cuti.online.panasonic.model.User;

import java.util.ArrayList;

public interface PengajuanCutiView {
    void onGetJenisCutiSuccess(ArrayList<JenisCuti> listJenisCuti);

    void onGetJenisCutiFailed(String message);

    void onGetProfileSuccess(User user);

    void onGetProfileFailed(String message);

    void onSubmitSuccess();

    void onSubmitFailed(String message);
}
