package com.yinglan.scg.entity.mine.personaldata;

import com.common.cklibrary.entity.BaseResult;

public class PersonalDataBean extends BaseResult<PersonalDataBean.DataBean> {


    /**
     * message : null
     * data : {"face":"http://img.shahaizhi.com/FuIb5rJYd2wozXtjE-ma507JRaOp","sex":1,"order_number":10,"service_level":"4.8","approve_status":1,"nickname":"天若有情x","guide_level_color":"DDDDDD","remark":"你好啊！","guide_level_name":"普通司导"}
     */
    public class DataBean {
        /**
         * face : http://img.shahaizhi.com/FuIb5rJYd2wozXtjE-ma507JRaOp
         * sex : 1
         * order_number : 10
         * service_level : 4.8
         * approve_status : 1
         * nickname : 天若有情x
         * guide_level_color : DDDDDD
         * remark : 你好啊！
         * guide_level_name : 普通司导
         */

        private String face;
        private int sex;
        private int order_number;
        private String service_level;
        private int approve_status;
        private String nickname;
        private String guide_level_color;
        private String remark;
        private String guide_level_name;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getOrder_number() {
            return order_number;
        }

        public void setOrder_number(int order_number) {
            this.order_number = order_number;
        }

        public String getService_level() {
            return service_level;
        }

        public void setService_level(String service_level) {
            this.service_level = service_level;
        }

        public int getApprove_status() {
            return approve_status;
        }

        public void setApprove_status(int approve_status) {
            this.approve_status = approve_status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGuide_level_color() {
            return guide_level_color;
        }

        public void setGuide_level_color(String guide_level_color) {
            this.guide_level_color = guide_level_color;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getGuide_level_name() {
            return guide_level_name;
        }

        public void setGuide_level_name(String guide_level_name) {
            this.guide_level_name = guide_level_name;
        }
    }
}
