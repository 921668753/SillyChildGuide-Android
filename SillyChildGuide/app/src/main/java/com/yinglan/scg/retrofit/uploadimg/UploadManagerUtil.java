package com.yinglan.scg.retrofit.uploadimg;

import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UploadManager;

public class UploadManagerUtil {


    //volatile的作用是： 作为指令关键字，确保本条指令不会因编译器的优化而省略，且要求每次直接读值.
    //一个定义为volatile的变量是说这变量可能会被意想不到地改变，
    //这样，编译器就不会去假设这个变量的值了。
    //精确地说就是，优化器在用到这个变量时必须每次都小心地重新读取这个变量的值，而不是使用保存在寄存器里的备份。
    private volatile static UploadManagerUtil uploadManagerUtil = null;

    private static UploadManager uploadManager;

    //构造函数 逻辑处理

    private UploadManagerUtil() {
        uploadManager = new UploadManager();
    }

    private UploadManagerUtil(Configuration config) {
        uploadManager = new UploadManager(config);
    }

    private UploadManagerUtil(Recorder recorder, KeyGenerator keyGen) {
        uploadManager = new UploadManager(recorder, keyGen);
    }

    /**
     * 不是很好，待改进
     *
     * @return
     */

    public static UploadManagerUtil getInstance() {
        dstroyInstance();
        //第一次判断是否为空
        if (uploadManagerUtil == null) {
            synchronized (UploadManagerUtil.class) {//锁
                //第二次判断是否为空 多线程同时走到这里的时候，需要这样优化处理
                if (uploadManagerUtil == null) {
                    uploadManagerUtil = new UploadManagerUtil();
                }
            }
        }

        return uploadManagerUtil;
    }

    public static UploadManagerUtil getInstance(Configuration config) {
        dstroyInstance();
        //第一次判断是否为空
        if (uploadManagerUtil == null) {
            synchronized (UploadManagerUtil.class) {//锁
                //第二次判断是否为空 多线程同时走到这里的时候，需要这样优化处理
                if (uploadManagerUtil == null) {
                    uploadManagerUtil = new UploadManagerUtil(config);
                }
            }
        }

        return uploadManagerUtil;
    }


    public static UploadManagerUtil getInstance(Recorder recorder, KeyGenerator keyGen) {
        dstroyInstance();
        //第一次判断是否为空
        if (uploadManagerUtil == null) {
            synchronized (UploadManagerUtil.class) {//锁
                //第二次判断是否为空 多线程同时走到这里的时候，需要这样优化处理
                if (uploadManagerUtil == null) {
                    uploadManagerUtil = new UploadManagerUtil(recorder, keyGen);
                }
            }
        }

        return uploadManagerUtil;
    }


    public UploadManager getUploadManager() {
        return uploadManager;
    }

    private static void dstroyInstance() {
        if (uploadManagerUtil != null) {
            uploadManagerUtil = null;
        }
        if (uploadManager != null) {
            uploadManager = null;
        }
    }

}
