package com.yinglan.scg.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;

/**
 * 用户信息
 * Created by Administrator on 2017/9/7.
 */

public class UserInfoBean extends BaseResult<UserInfoBean.DataBean> implements Serializable {


    public static class DataBean {
        /**
         * face : http://img.shahaizhi.com/FhNs89NJnXwRP3DiTJZObPms2cWa
         * sex : 1
         * order_number : 10
         * service_level : 4.8
         * nickname : 天若有情
         * remark : 你好啊！
         * guide_level_name : 黄金司导
         */
        private String face;
        private int sex;
        private int order_number;
        private boolean model_status;
        private String guide_level_color;
        private String service_level;
        private String nickname;
        private String remark;
        private String guide_level_name;
        private int approve_status;
        private String invite_code;

        public boolean isModel_status() {
            return model_status;
        }

        public void setModel_status(boolean model_status) {
            this.model_status = model_status;
        }

        public String getGuide_level_color() {
            return guide_level_color;
        }

        public void setGuide_level_color(String guide_level_color) {
            this.guide_level_color = guide_level_color;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getOrder_number() {
            return order_number;
        }

        public void setOrder_number(int order_number) {
            this.order_number = order_number;
        }

        public String getService_level() {
            return service_level;
        }

        public void setService_level(String service_level) {
            this.service_level = service_level;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getGuide_level_name() {
            return guide_level_name;
        }

        public void setGuide_level_name(String guide_level_name) {
            this.guide_level_name = guide_level_name;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }
    }
}
