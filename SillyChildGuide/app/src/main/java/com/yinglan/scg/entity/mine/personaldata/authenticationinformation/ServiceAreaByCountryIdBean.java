package com.yinglan.scg.entity.mine.personaldata.authenticationinformation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ServiceAreaByCountryIdBean extends BaseResult<List<ServiceAreaByCountryIdBean.DataBean>> {


    public class DataBean {
        /**
         * city_id : 13
         * city_name : 东京
         * city_picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private int city_id;
        private String city_name;
        private String city_picture;

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_picture() {
            return city_picture;
        }

        public void setCity_picture(String city_picture) {
            this.city_picture = city_picture;
        }
    }
}

