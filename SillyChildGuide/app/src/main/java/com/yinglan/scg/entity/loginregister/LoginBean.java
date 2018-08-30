package com.yinglan.scg.entity.loginregister;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ruitu on 2016/8/27.
 */

public class LoginBean extends BaseResult<LoginBean.DataBean> {


    public class DataBean {
        /**
         * result : true
         * face :
         * level : 普通会员
         * rong_cloud : uebBjWGteL5w0pcJ3K6KcFKqBBz4L12yws/FYu6flNARzmCj3SW3qSG/LbY2sBJ6F0iFqZ+9ojHueSr25fWEow==
         * username : 17051335257
         */

        @SerializedName("result")
        private String resultX;
        private String face;
        private String level;
        private String rong_cloud;
        private String username;

        public String getResultX() {
            return resultX;
        }

        public void setResultX(String resultX) {
            this.resultX = resultX;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getRong_cloud() {
            return rong_cloud;
        }

        public void setRong_cloud(String rong_cloud) {
            this.rong_cloud = rong_cloud;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
