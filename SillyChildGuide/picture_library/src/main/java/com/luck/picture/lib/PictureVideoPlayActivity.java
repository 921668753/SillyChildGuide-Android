package com.luck.picture.lib;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.kymjs.common.FileUtils;
import com.kymjs.common.Log;
import com.luck.picture.lib.widget.MediaController;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.widget.PLVideoView;

public class PictureVideoPlayActivity extends PictureBaseActivity implements PLOnInfoListener, PLOnErrorListener, PLOnPreparedListener, PLOnCompletionListener, View.OnClickListener {

    private String video_path = "";
    private ImageView picture_left_back;
    private MediaController mMediaController;
    private PLVideoView mVideoView;
    private ImageView iv_play;

    /**
     * 视频文件缓存保存路径存放的文件名
     */
    public static String VIDEOCACHE = "SHZY/VideoCache";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_activity_video_play);
        video_path = getIntent().getStringExtra("video_path");
        picture_left_back = (ImageView) findViewById(R.id.picture_left_back);
        mVideoView = (PLVideoView) findViewById(R.id.video_view);
        mVideoView.setBackgroundColor(Color.BLACK);
        iv_play = (ImageView) findViewById(R.id.iv_play);
        AVOptions options = new AVOptions();

        /**
         * 若设置为 1，则底层会进行一些针对直播流的优化
         */
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);

        /**
         * 快开模式，启用后会加快该播放器实例再次打开相同协议的视频流的速度
         */
        options.setInteger(AVOptions.KEY_FAST_OPEN, 1);
        String downloadDirectoryPath = FileUtils.getSaveFolder(VIDEOCACHE).getAbsolutePath();
        options.setString(AVOptions.KEY_CACHE_DIR, downloadDirectoryPath);
        mVideoView.setAVOptions(options);
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
        mMediaController.setMediaPlayer(mVideoView);
        mMediaController.setAnchorView(mVideoView);

        mVideoView.setOnErrorListener(this);
        mVideoView.setOnCompletionListener(this);

        mVideoView.setOnInfoListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setVideoPath(video_path);

        picture_left_back.setOnClickListener(this);
        iv_play.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        mVideoView.start();
        super.onStart();
    }

    @Override
    public void onPause() {
        mVideoView.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mVideoView.stopPlayback();
        mMediaController = null;
        mVideoView = null;
        super.onDestroy();
    }


    @Override
    public void onCompletion() {
        iv_play.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.picture_left_back) {
            finish();
        } else if (id == R.id.iv_play) {
            mVideoView.start();
            iv_play.setVisibility(View.GONE);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ContextWrapper(newBase) {
            @Override
            public Object getSystemService(String name) {
                if (Context.AUDIO_SERVICE.equals(name)) {
                    return getApplicationContext().getSystemService(name);
                }
                return super.getSystemService(name);
            }
        });
    }

    @Override
    public void onPrepared(int i) {
        iv_play.setVisibility(View.GONE);
        mVideoView.start();
    }

    @Override
    public boolean onError(int i) {
        return false;
    }


    @Override
    public void onInfo(int what, int extra) {
        Log.i("PictureVideoPlayActivity", "OnInfo, what = " + what + ", extra = " + extra);
        switch (what) {
            case PLOnInfoListener.MEDIA_INFO_BUFFERING_START:
                break;
            case PLOnInfoListener.MEDIA_INFO_BUFFERING_END:
                break;
            case PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START:
                //    iv_play.setVisibility(View.GONE);
                break;
            case PLOnInfoListener.MEDIA_INFO_AUDIO_RENDERING_START:
                break;
            case PLOnInfoListener.MEDIA_INFO_VIDEO_FRAME_RENDERING:
                Log.i("PictureVideoPlayActivity", "video frame rendering, ts = " + extra);
                break;
            case PLOnInfoListener.MEDIA_INFO_AUDIO_FRAME_RENDERING:
                Log.i("PictureVideoPlayActivity", "audio frame rendering, ts = " + extra);
                break;
            case PLOnInfoListener.MEDIA_INFO_VIDEO_GOP_TIME:
                Log.i("PictureVideoPlayActivity", "Gop Time: " + extra);
                break;
            case PLOnInfoListener.MEDIA_INFO_SWITCHING_SW_DECODE:
                Log.i("PictureVideoPlayActivity", "Hardware decoding failure, switching software decoding!");
                break;
            case PLOnInfoListener.MEDIA_INFO_METADATA:
                Log.i("PictureVideoPlayActivity", mVideoView.getMetadata().toString());
                break;
            case PLOnInfoListener.MEDIA_INFO_VIDEO_BITRATE:
            case PLOnInfoListener.MEDIA_INFO_VIDEO_FPS:
                //   updateStatInfo();
                break;
            case PLOnInfoListener.MEDIA_INFO_CONNECTED:
                Log.i("PictureVideoPlayActivity", "Connected !");
                break;
            case PLOnInfoListener.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                Log.i("PictureVideoPlayActivity", "Rotation changed: " + extra);
            default:
                break;
        }
    }
}
