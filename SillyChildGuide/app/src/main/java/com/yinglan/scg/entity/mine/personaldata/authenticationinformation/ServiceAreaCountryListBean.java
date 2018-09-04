package com.yinglan.scg.entity.mine.personaldata.authenticationinformation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ServiceAreaCountryListBean extends BaseResult<List<ServiceAreaCountryListBean.DataBean>> {


    public class DataBean {
        /**
         * country_id : 8
         * country_name : 中国
         */
        private int isSelected;
        private int country_id;
        private String country_name;

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }
    }
}
