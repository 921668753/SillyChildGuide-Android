package com.yinglan.scg.entity.main;

import com.common.cklibrary.entity.BaseResult;

/**
 * 用户信息
 * Created by Administrator on 2017/9/7.
 */

public class UserInfoBean extends BaseResult<UserInfoBean.DataBean> {


    public class DataBean {
        /**
         * nickname :
         * store_name :
         * store_id :
         * disabled :
         * face : null
         * order_total : null
         * store_level : null
         * lv_id : null
         * lv_name : null
         * store_logo : null
         */

        private String nickname;
        private String store_name;
        private String store_id;
        private String disabled;
        private String face;
        private String order_total;
        private String store_level;
        private String lv_id;
        private String lv_name;
        private String store_logo;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getDisabled() {
            return disabled;
        }

        public void setDisabled(String disabled) {
            this.disabled = disabled;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getOrder_total() {
            return order_total;
        }

        public void setOrder_total(String order_total) {
            this.order_total = order_total;
        }

        public String getStore_level() {
            return store_level;
        }

        public void setStore_level(String store_level) {
            this.store_level = store_level;
        }

        public String getLv_id() {
            return lv_id;
        }

        public void setLv_id(String lv_id) {
            this.lv_id = lv_id;
        }

        public String getLv_name() {
            return lv_name;
        }

        public void setLv_name(String lv_name) {
            this.lv_name = lv_name;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }
    }
}
