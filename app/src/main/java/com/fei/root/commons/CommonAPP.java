package com.fei.root.commons;

import com.fei.root.common.MultiApplication;

/**
 * Created by PengFeifei on 17-8-9.
 */

public class CommonAPP extends MultiApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }
}
