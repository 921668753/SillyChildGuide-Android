package com.yinglan.scg.entity.mine.myvehicle.model;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ModelNameListBean extends BaseResult<List<ModelNameListBean.DataBean>> {


    public class DataBean {
        /**
         * model_name_id : 31
         * model_name : 高尔夫
         * picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private int model_name_id;
        private String model_name;
        private String picture;

        public int getModel_name_id() {
            return model_name_id;
        }

        public void setModel_name_id(int model_name_id) {
            this.model_name_id = model_name_id;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}

