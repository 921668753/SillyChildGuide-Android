package com.yinglan.scg.entity.mine.personaldata.authenticationinformation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ServiceAreaByCountryIdBean extends BaseResult<List<ServiceAreaByCountryIdBean.DataBean>> {

    public class DataBean {
        /**
         * country_id : 8
         * country_name : 中国
         * region_id : 9
         * region_name : 上海
         * airport_id : 10
         * airport_name : 浦东国际机场
         * airport_picture : http://img.shahaizhi.com/%28null%291530269095
         */

        private int country_id;
        private String country_name;
        private int region_id;
        private String region_name;
        private int airport_id;
        private String airport_name;
        private String picture;

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

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public int getAirport_id() {
            return airport_id;
        }

        public void setAirport_id(int airport_id) {
            this.airport_id = airport_id;
        }

        public String getAirport_name() {
            return airport_name;
        }

        public void setAirport_name(String airport_name) {
            this.airport_name = airport_name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}

