package com.dc.kouwei20190120.app;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {
    /**
     * 第三方软件初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //5c442affb465f54b2400022c
        UMConfigure.init(this,"5c442affb465f54b2400022c"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
