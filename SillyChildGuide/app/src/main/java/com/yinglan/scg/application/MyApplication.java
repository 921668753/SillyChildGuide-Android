package com.yinglan.scg.application;

import com.yinglan.scg.BuildConfig;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 程序入口
 * Created by Administrator on 2016/5/13.
 */
public class MyApplication extends TinkerApplication {

    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, BuildConfig.APPLICATION_ID + ".application.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

}