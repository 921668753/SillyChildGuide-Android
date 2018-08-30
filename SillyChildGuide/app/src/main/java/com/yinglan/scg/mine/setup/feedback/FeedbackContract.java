package com.yinglan.scg.mine.setup.feedback;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface FeedbackContract {

    interface Presenter extends BasePresenter {
        /**
         * 上传图片
         *
         * @param imgPath
         */
        void upPictures(String imgPath);

        /**
         * 提交反馈
         */
        void postAdvice(String feedType, String content, List<String> imgs);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
