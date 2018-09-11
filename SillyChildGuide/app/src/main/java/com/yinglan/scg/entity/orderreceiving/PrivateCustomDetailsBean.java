package com.yinglan.scg.entity.orderreceiving;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

import com.yinglan.scg.entity.orderreceiving.TransferDetailsBean.DataBean.ModelListBean;

public class PrivateCustomDetailsBean extends BaseResult<PrivateCustomDetailsBean.DataBean> {


    public class DataBean {
        /**
         * model_list : [{"id":2,"license_plate":"豫G77777","model_name":"迈腾","is_default":0},{"id":4,"license_plate":"京A777777","model_name":"帕萨特","is_default":1}]
         * order_number : SHZ20180825153754648084000
         * end_time : 1539273600
         * destination_name : 行程结束地
         * order_price : 0.01
         * title : 45定制游
         * start_time : 1538379447
         * schedule : <p>这个是行程安排</p>
         * booking_request : 3成人1行李
         * subtitle : 3人1行李
         * book_comment : <p>这个是订购说明</p>
         * origin_name : 行程开始出发地
         * price_comment : <p><strong><span style="font-size: 18px;">费用包含</span></strong></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">基础服务费：基础服务费、基础服务费、基础服务费、基础服务费、基础服务费。</span></p></li><li><p><span style="font-size: 14px;">附加费用：附加费用、<span style="font-size: 18px;">附加费用、附加费用、附加费用、附加费用。</span></span></p><p><span style="font-size: 18px;"></span></p></li></ul><p><span style="font-size: 18px;"><span style="font-size: 18px;"></span><strong>费用不包含</strong></span></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">未提及费用</span><span style="font-size: 18px;"><br/></span></p></li></ul>
         */

        private String order_number;
        private String end_time;
        private String destination_name;
        private String order_price;
        private String title;
        private String travel_preference;
        private String repast_preference;
        private String stay_preference;
        private String start_time;
        private String schedule;
        private String booking_request;
        private String subtitle;
        private String book_comment;
        private String origin_name;
        private String price_comment;
        private List<ModelListBean> model_list;

        public String getTravel_preference() {
            return travel_preference;
        }

        public void setTravel_preference(String travel_preference) {
            this.travel_preference = travel_preference;
        }

        public String getRepast_preference() {
            return repast_preference;
        }

        public void setRepast_preference(String repast_preference) {
            this.repast_preference = repast_preference;
        }

        public String getStay_preference() {
            return stay_preference;
        }

        public void setStay_preference(String stay_preference) {
            this.stay_preference = stay_preference;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getDestination_name() {
            return destination_name;
        }

        public void setDestination_name(String destination_name) {
            this.destination_name = destination_name;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }

        public String getBooking_request() {
            return booking_request;
        }

        public void setBooking_request(String booking_request) {
            this.booking_request = booking_request;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getBook_comment() {
            return book_comment;
        }

        public void setBook_comment(String book_comment) {
            this.book_comment = book_comment;
        }

        public String getOrigin_name() {
            return origin_name;
        }

        public void setOrigin_name(String origin_name) {
            this.origin_name = origin_name;
        }

        public String getPrice_comment() {
            return price_comment;
        }

        public void setPrice_comment(String price_comment) {
            this.price_comment = price_comment;
        }

        public List<ModelListBean> getModel_list() {
            return model_list;
        }

        public void setModel_list(List<ModelListBean> model_list) {
            this.model_list = model_list;
        }
    }
}
