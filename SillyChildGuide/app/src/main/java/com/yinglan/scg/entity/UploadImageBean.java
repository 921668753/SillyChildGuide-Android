package com.yinglan.scg.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by ruitu on 2016/9/17.
 */

public class UploadImageBean extends BaseResult<UploadImageBean.ResultBean> {

    public class ResultBean {
        /**
         * file : {"url":"http://opmnz562z.bkt.clouddn.com/d602a85bdcedb305c0d2c6afabe220dd.png"}
         */

        private FileBean file;

        public FileBean getFile() {
            return file;
        }

        public void setFile(FileBean file) {
            this.file = file;
        }

        public class FileBean {
            /**
             * url : http://opmnz562z.bkt.clouddn.com/d602a85bdcedb305c0d2c6afabe220dd.png
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}