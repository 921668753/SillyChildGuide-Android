package com.common.cklibrary.utils.glide;

import android.content.Context;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.FileUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;


/**
 * Created by YaphetZhao
 * on 2016/12/19.
 * QQ:11613371
 * GitHub:https://github.com/YaphetZhao
 * Glide缓存工具类
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class GlideCatchUtil {
    private static GlideCatchUtil instance;
    private String sfore;
    private boolean deletetrue;

    public static GlideCatchUtil getInstance() {
        if (null == instance) {
            instance = new GlideCatchUtil();
        }
        return instance;
    }

//    // 获取Glide磁盘缓存大小
//    public String getStrCacheSize() {
//        try {
//            return getFormatSize(getFolderSize(FileUtils.getSaveFolder(StringConstants.PHOTOCACHE)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return KJActivityStack.create().topActivity().getString(R.string.forFailure);
//        }
//    }

    // 获取Glide磁盘缓存大小
    public long getCacheSize() {
        try {
            return getFolderSize(FileUtils.getSaveFolder(StringConstants.PHOTOCACHE));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 清除Glide磁盘缓存，自己获取缓存文件夹并删除方法
    public boolean cleanCatchDisk() {
        return deleteFolderFile(FileUtils.getSaveFolder(StringConstants.PHOTOCACHE).getAbsolutePath(), true);
    }

    // 清除Glide磁盘缓存，自己获取裁剪后的图片文件夹并删除图片文件的方法
    public boolean cleanImageDisk() {
        return deleteFolderFile(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath(), true);
    }

    // 清除图片磁盘缓存，调用Glide自带方法
    public boolean clearCacheDiskSelf() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(KJActivityStack.create().topActivity()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(KJActivityStack.create().topActivity()).clearDiskCache();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 清除Glide内存缓存
    public boolean clearCacheMemory() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(KJActivityStack.create().topActivity()).clearMemory();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // 获取指定文件夹内所有文件大小的和
    private long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return size;
        }
        return size;
    }

    // 格式化单位
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    // 按目录删除文件夹文件方法
    private boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
        try {
            File file = new File(filePath);
            if (file.exists()&&file.isDirectory()) {
                File files[] = file.listFiles();
                for (File file1 : files) {
                    deleteFolderFile(file1.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取图片缓存路径
    @SuppressWarnings("deprecation")
    public String getFilePath(Context context, String url) {
        String path = null;
        try {
            File future = Glide.with(context)
                    .load(url)
                    //  .downloadOnly(500, 500)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            path = future.getAbsolutePath();
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }

    // 获取缓存图片
//    private Bitmap getBitmap(Context context, String url) {
//
//        Bitmap myBitmap = GlideApp.with(context)
//                .load(url)
//                .apply(fitCenterTransform().center)
//                .asBitmap() //必须
//                .into(500, 500)
//                .get();
//        return myBitmap;
//    }


}
