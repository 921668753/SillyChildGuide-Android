package com.yinglan.scg.entity.mine.myvehicle;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class VehicleDetailsBean extends BaseResult<VehicleDetailsBean.DataBean> {


    public class DataBean {

        /**
         * message_list : [{"approve_message":"提交车辆信息","create_time":1536211046}]
         * model : {"id":1,"member_id":14,"model_picture":["http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0"],"model_brand":"大众","model_brand_id":1,"model_name":"途观L","model_name_id":18,"passenger_number":1,"baggage_number":3,"model_year":"1536211026","license_plate":"A123","is_insurance":1,"submit_time":1536211046,"approve_status":1}
         */
        private ModelBean model;
        private List<MessageListBean> message_list;

        public ModelBean getModel() {
            return model;
        }

        public void setModel(ModelBean model) {
            this.model = model;
        }

        public List<MessageListBean> getMessage_list() {
            return message_list;
        }

        public void setMessage_list(List<MessageListBean> message_list) {
            this.message_list = message_list;
        }

        public class ModelBean {
            /**
             * id : 1
             * member_id : 14
             * model_picture : ["http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0"]
             * model_brand : 大众
             * model_brand_id : 1
             * model_name : 途观L
             * model_name_id : 18
             * passenger_number : 1
             * baggage_number : 3
             * model_year : 1536211026
             * license_plate : A123
             * is_insurance : 1
             * submit_time : 1536211046
             * approve_status : 1
             */

            private int id;
            private int member_id;
            private String model_brand;
            private int model_brand_id;
            private String model_name;
            private int model_name_id;
            private int passenger_number;
            private int baggage_number;
            private String model_year;
            private String license_plate;
            private int is_insurance;
            private int submit_time;
            private int approve_status;
            private List<String> model_pictures;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public String getModel_brand() {
                return model_brand;
            }

            public void setModel_brand(String model_brand) {
                this.model_brand = model_brand;
            }

            public int getModel_brand_id() {
                return model_brand_id;
            }

            public void setModel_brand_id(int model_brand_id) {
                this.model_brand_id = model_brand_id;
            }

            public String getModel_name() {
                return model_name;
            }

            public void setModel_name(String model_name) {
                this.model_name = model_name;
            }

            public int getModel_name_id() {
                return model_name_id;
            }

            public void setModel_name_id(int model_name_id) {
                this.model_name_id = model_name_id;
            }

            public int getPassenger_number() {
                return passenger_number;
            }

            public void setPassenger_number(int passenger_number) {
                this.passenger_number = passenger_number;
            }

            public int getBaggage_number() {
                return baggage_number;
            }

            public void setBaggage_number(int baggage_number) {
                this.baggage_number = baggage_number;
            }

            public String getModel_year() {
                return model_year;
            }

            public void setModel_year(String model_year) {
                this.model_year = model_year;
            }

            public String getLicense_plate() {
                return license_plate;
            }

            public void setLicense_plate(String license_plate) {
                this.license_plate = license_plate;
            }

            public int getIs_insurance() {
                return is_insurance;
            }

            public void setIs_insurance(int is_insurance) {
                this.is_insurance = is_insurance;
            }

            public int getSubmit_time() {
                return submit_time;
            }

            public void setSubmit_time(int submit_time) {
                this.submit_time = submit_time;
            }

            public int getApprove_status() {
                return approve_status;
            }

            public void setApprove_status(int approve_status) {
                this.approve_status = approve_status;
            }

            public List<String> getModel_pictures() {
                return model_pictures;
            }

            public void setModel_pictures(List<String> model_pictures) {
                this.model_pictures = model_pictures;
            }
        }

        public class MessageListBean {

            /**
             * approve_message : 提交车辆信息
             * create_time : 1536211046
             */
            private String approve_message;
            private int create_time;

            public String getApprove_message() {
                return approve_message;
            }

            public void setApprove_message(String approve_message) {
                this.approve_message = approve_message;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }
        }
    }
}
