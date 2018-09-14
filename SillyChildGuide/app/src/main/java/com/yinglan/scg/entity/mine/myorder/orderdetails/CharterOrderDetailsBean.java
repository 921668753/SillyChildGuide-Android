package com.yinglan.scg.entity.mine.myorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;


import com.yinglan.scg.entity.mine.myorder.orderdetails.TransferOrderDetailsBean.DataBean.ReviewDataBean;


public class CharterOrderDetailsBean extends BaseResult<CharterOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * order_number : SHZ2018082712375753977508
         * end_time : 1537163200
         * destination_name : 鄂尔多斯
         * order_price : 0.04
         * title : 日本日本市内包车
         * connect_number : 15515916027
         * start_time : 1535904000
         * order_status : 1
         * license_plate : A123
         * model_name : 途观
         * booking_request : 3成人1行李
         * subtitle : 3人1行李
         * contact : 彪哥
         * book_comment : <p><strong><span style="font-size: 18px;">费用包含</span></strong></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">基础服务费：基础服务费、基础服务费、基础服务费、基础服务费、基础服务费。</span></p></li><li><p><span style="font-size: 14px;">附加费用：附加费用、<span style="font-size: 18px;">附加费用、附加费用、附加费用、附加费用。</span></span></p><p><span style="font-size: 18px;"></span></p></li></ul><p><span style="font-size: 18px;"><span style="font-size: 18px;"></span><strong>费用不包含</strong></span></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">未提及费用</span><span style="font-size: 18px;"><br/></span></p></li></ul>
         * origin_name : 上海
         * price_comment : <p><strong><span style="font-size: 18px;">费用包含</span></strong></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">基础服务费：基础服务费、基础服务费、基础服务费、基础服务费、基础服务费。</span></p></li><li><p><span style="font-size: 14px;">附加费用：附加费用、<span style="font-size: 18px;">附加费用、附加费用、附加费用、附加费用。</span></span></p><p><span style="font-size: 18px;"></span></p></li></ul><p><span style="font-size: 18px;"><span style="font-size: 18px;"></span><strong>费用不包含</strong></span></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">未提及费用</span><span style="font-size: 18px;"><br/></span></p></li></ul>
         */

        private String order_number;
        private String end_time;
        private String destination_name;
        private String order_price;
        private String title;
        private String connect_number;
        private String start_time;
        private int order_status;
        private String license_plate;
        private String model_name;
        private String booking_request;
        private String subtitle;
        private String contact;
        private String book_comment;
        private String origin_name;
        private String price_comment;
        private ReviewDataBean review_data;

        public ReviewDataBean getReview_data() {
            return review_data;
        }

        public void setReview_data(ReviewDataBean review_data) {
            this.review_data = review_data;
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

        public String getConnect_number() {
            return connect_number;
        }

        public void setConnect_number(String connect_number) {
            this.connect_number = connect_number;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getLicense_plate() {
            return license_plate;
        }

        public void setLicense_plate(String license_plate) {
            this.license_plate = license_plate;
        }

        public String getModel_name() {
            return model_name;
        }

        public void setModel_name(String model_name) {
            this.model_name = model_name;
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

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
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
    }
}
