package com.yinglan.scg.entity.mine.myvehicle;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyVehicleBean extends BaseResult<List<MyVehicleBean.DataBean>> {


    public class DataBean {
        /**
         * id : 1
         * model_picture : http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
         * model_brand : 大众
         * model_name : 途观L
         * passenger_number : 1
         * model_status : 1
         */

        private int id;
        private String model_picture;
        private String model_brand;
        private String model_name;
        private int passenger_number;
        private int model_status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModel_picture() {
            return model_picture;
        }

        public void setModel_picture(String model_picture) {
            this.model_picture = model_picture;
        }

        public String getModel_brand() {
            return model_brand;
        }

        public void setModel_brand(String model_brand) {
            this.model_brand = model_brand;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
        }

        public int getPassenger_number() {
            return passenger_number;
        }

        public void setPassenger_number(int passenger_number) {
            this.passenger_number = passenger_number;
        }

        public int getModel_status() {
            return model_status;
        }

        public void setModel_status(int model_status) {
            this.model_status = model_status;
        }
    }
}
