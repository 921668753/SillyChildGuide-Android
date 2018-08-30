package com.common.cklibrary.common;

/**
 * 用于存放字符串常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class StringConstants {
    /**
     * 存放的文件名
     */
    public static String FILENAME = "shzyShare";
    /**
     * 文件缓存路径存放的文件名-----图片以及URL请求缓存路径
     */
    public static String CACHEPATH = "SHZY/Cache";
    // 文件缓存路径存放，500M，根据自己的需求进行修改(单位bt)
    public static final int CACHE_SIZE = 500 * 1000 * 1000;
    /**
     * 图片缓存路径 存放的文件名
     */
    public static String PHOTOCACHE = "SHZY/PhotoCache";
    /**
     * 图片保存路径存放的文件名
     */
    public static String PHOTOPATH = "SHZY/PhonePhoto";
    /**
     * 视频文件缓存保存路径存放的文件名
     */
    public static String VIDEOCACHE = "SHZY/VideoCache";
    /**
     * 下载文件保存路径的文件名
     */
    public static String DOWNLOADPATH = "SHZY/Download";
    /**
     * 错误日志存放位置ERRORLOG
     */
    public static String ERRORLOG = "SHZY/PhoneLog";

    // 图片缓存最大容量，150M，根据自己的需求进行修改 自定义内置磁盘缓存大小.
    public static final int GLIDE_CATCH_SIZE = 1000 * 1000 * 1000;
    // 图片内存缓存最大容量，150M，根据自己的需求进行修改
    public static final int MEMORY_CATCH_SIZE = 150 * 1000 * 1000;
    // 图片缓存最大容量，150M，根据自己的需求进行修改
    public static final int BITMAP_POOL_SIZE = 1024 * 32;

    // 图片压缩，5M，根据自己的需求进行修改(单位b)
    public static final int COMPRESSION_SIZE = 1024 * 1024 * 5;

}
