package com.cuti.online.panasonic.interfaces;

import com.google.android.gms.tasks.Task;

/**
 * Created by Gustu Maulana Firmansyah on 10,July,2021  gustumaulanaf@gmail.com
 **/
public interface RegisterView {
    void onSuccess(Task<Void> task);

    void onFailed(String s);
}
