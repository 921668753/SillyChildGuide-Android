package com.yinglan.scg.entity.mine.myvehicle.model;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ModelBrandListBean extends BaseResult<List<ModelBrandListBean.DataBean>> {


    public class DataBean {
        /**
         * country_id : 8
         * country_name : 中国
         */
        private int isSelected;
        private int model_brand_id;
        private String model_brand;

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public int getModel_brand_id() {
            return model_brand_id;
        }

        public void setModel_brand_id(int model_brand_id) {
            this.model_brand_id = model_brand_id;
        }

        public String getModel_brand() {
            return model_brand;
        }

        public void setModel_brand(String model_brand) {
            this.model_brand = model_brand;
        }
    }
}
