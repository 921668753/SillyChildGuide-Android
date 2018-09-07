package com.yinglan.scg.entity.loginregister;


import com.common.cklibrary.entity.BaseResult;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by Admin on 2017/9/6.
 */

public class SelectCountryCodeBean extends BaseResult<List<SelectCountryCodeBean.DataBean>> {


    public class DataBean extends BaseIndexPinyinBean {
        /**
         * country_name : Afghanistan
         * china_name : 阿富汗
         * code : AF
         * country_code : 93
         * price : 0.61
         */

        private String country_name;
        private String china_name;
        private String code;
        private String country_code;
        private String price;

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getChina_name() {
            return china_name;
        }

        public void setChina_name(String china_name) {
            this.china_name = china_name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String getTarget() {
            return china_name;
        }
    }

}

