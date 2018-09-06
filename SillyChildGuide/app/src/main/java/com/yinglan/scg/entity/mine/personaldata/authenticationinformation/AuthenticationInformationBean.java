package com.yinglan.scg.entity.mine.personaldata.authenticationinformation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class AuthenticationInformationBean extends BaseResult<AuthenticationInformationBean.DataBean> {


    public class DataBean {
        /**
         * message_list : [{"approve_message":"提交证件信息，待审核","message_date":1536197311}]
         * certification : {"car_inspection_certificate":"http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0","driver_license":"http://img.shahaizhi.com/FtTGZXJi4o8zmxTIWyghdshzntQ5?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0","guide_card":"null?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0","iD_card":"http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0","id":3,"member_id":14,"service_city_id":13,"service_city_name":"东京","service_country_id":12,"service_country_name":"日本","submit_time":1536197311,"yacht_driving_license":"null?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0"}
         */

        private CertificationBean certification;
        private List<MessageListBean> message_list;

        public CertificationBean getCertification() {
            return certification;
        }

        public void setCertification(CertificationBean certification) {
            this.certification = certification;
        }

        public List<MessageListBean> getMessage_list() {
            return message_list;
        }

        public void setMessage_list(List<MessageListBean> message_list) {
            this.message_list = message_list;
        }

        public class CertificationBean {
            /**
             * car_inspection_certificate : http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
             * driver_license : http://img.shahaizhi.com/FtTGZXJi4o8zmxTIWyghdshzntQ5?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
             * guide_card : null?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
             * iD_card : http://img.shahaizhi.com/FmxpfIJCnIfRp572o8Yg288VcP6i?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
             * id : 3
             * member_id : 14
             * service_city_id : 13
             * service_city_name : 东京
             * service_country_id : 12
             * service_country_name : 日本
             * submit_time : 1536197311
             * yacht_driving_license : null?imageMogr2/auto-orient/thumbnail/!20p/blur/1x0
             */

            private String car_inspection_certificate;
            private String driver_license;
            private String guide_card;
            private String iD_card;
            private int id;
            private int member_id;
            private int service_city_id;
            private String service_city_name;
            private int service_country_id;
            private String service_country_name;
            private int submit_time;
            private String yacht_driving_license;

            public String getCar_inspection_certificate() {
                return car_inspection_certificate;
            }

            public void setCar_inspection_certificate(String car_inspection_certificate) {
                this.car_inspection_certificate = car_inspection_certificate;
            }

            public String getDriver_license() {
                return driver_license;
            }

            public void setDriver_license(String driver_license) {
                this.driver_license = driver_license;
            }

            public String getGuide_card() {
                return guide_card;
            }

            public void setGuide_card(String guide_card) {
                this.guide_card = guide_card;
            }

            public String getID_card() {
                return iD_card;
            }

            public void setID_card(String iD_card) {
                this.iD_card = iD_card;
            }

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

            public int getService_city_id() {
                return service_city_id;
            }

            public void setService_city_id(int service_city_id) {
                this.service_city_id = service_city_id;
            }

            public String getService_city_name() {
                return service_city_name;
            }

            public void setService_city_name(String service_city_name) {
                this.service_city_name = service_city_name;
            }

            public int getService_country_id() {
                return service_country_id;
            }

            public void setService_country_id(int service_country_id) {
                this.service_country_id = service_country_id;
            }

            public String getService_country_name() {
                return service_country_name;
            }

            public void setService_country_name(String service_country_name) {
                this.service_country_name = service_country_name;
            }

            public int getSubmit_time() {
                return submit_time;
            }

            public void setSubmit_time(int submit_time) {
                this.submit_time = submit_time;
            }

            public String getYacht_driving_license() {
                return yacht_driving_license;
            }

            public void setYacht_driving_license(String yacht_driving_license) {
                this.yacht_driving_license = yacht_driving_license;
            }
        }

        public class MessageListBean {
            /**
             * approve_message : 提交证件信息，待审核
             * message_date : 1536197311
             */

            private String approve_message;
            private int message_date;

            public String getApprove_message() {
                return approve_message;
            }

            public void setApprove_message(String approve_message) {
                this.approve_message = approve_message;
            }

            public int getMessage_date() {
                return message_date;
            }

            public void setMessage_date(int message_date) {
                this.message_date = message_date;
            }
        }
    }
}
