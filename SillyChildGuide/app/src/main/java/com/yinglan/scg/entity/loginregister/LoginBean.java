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
         * face : http://img.shahaizhi.com/FhNs89NJnXwRP3DiTJZObPms2cWa
         * shz : SHZ14
         * level : 黄金会员
         * approve_status : 2
         * rong_cloud : WzOcwBsEyK3NVwyOtMlufVrV/8ctfhpCZvfH8p9x+KZ7vtLDHzh38uyKNu08kGZP5GR91/etQ3iZ7IsldGLgtg==
         * username : 18550875927
         */

        @SerializedName("result")
        private String resultX;
        private String face;
        private String shz;
        private String level;
        private int approve_status;
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

        public String getShz() {
            return shz;
        }

        public void setShz(String shz) {
            this.shz = shz;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
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
