package com.yinglan.scg.entity;

import com.common.cklibrary.entity.BaseResult;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by Admin on 2017/9/6.
 */

public class SelectCountryBean extends BaseResult<List<SelectCountryBean.ResultBean>> {


    public class ResultBean extends BaseIndexPinyinBean {
        /**
         * id : 214
         * country : 中国
         * mobile_prefix : 86
         * area : 亚洲
         */

        private int id;
        private String country;
        private String mobile_prefix;
        private String area;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getMobile_prefix() {
            return mobile_prefix;
        }

        public void setMobile_prefix(String mobile_prefix) {
            this.mobile_prefix = mobile_prefix;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        @Override
        public String getTarget() {
            return country;
        }
    }
}

