package com.common.cklibrary.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * 获取到缓存的图片显示在imageView
 * <p>
 * new GetImageCacheTask(this,imageView).execute(“url”);
 * <p>
 * Created by Administrator on 2017/5/21.
 */

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class GetImageCacheAsyncTask extends AsyncTask<String, Void, File> {
    private final Context context;
    private final View view;

    public GetImageCacheAsyncTask(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected File doInBackground(String... params) {
        String imgUrl = params[0];
        try {
            return Glide.with(context)
                    .load(imgUrl)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(File result) {
        if (result == null) {
            return;
        }
        //此path就是对应文件的缓存路径
        String path = result.getPath();
        Log.e("path", path);
        Bitmap bmp = BitmapFactory.decodeFile(path);
        ((ImageView) view).setImageBitmap(bmp);
    }


}
