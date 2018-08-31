package com.yinglan.scg.mine.setup.feedback;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface FeedbackContract {

    interface Presenter extends BasePresenter {

        /**
         * 提交反馈
         */
        void postAdvice(String feedType, String content, List<LocalMedia> selectList);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
