package com.yinglan.scg.entity.mine.myorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

import com.yinglan.scg.entity.mine.myorder.orderdetails.TransferOrderDetailsBean.DataBean.ReviewDataBean;
import com.yinglan.scg.entity.mine.myorder.orderdetails.TransferOrderDetailsBean.DataBean.GuideReviewDataBean;

public class LineOrderDetailsBean extends BaseResult<LineOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * order_number : SHZ20180829095839568812511
         * end_time : 1537372800
         * destination_name : 目的地
         * order_price : 0.01
         * title : 大阪3天2夜精选路线
         * connect_number : 17051335257
         * start_time : 1535644800
         * order_status : 1
         * license_plate : 豫G77777
         * model_name : 迈腾
         * booking_request : 1成人0行李
         * subtitle : 1人0行李
         * contact : ck
         * book_comment : <p><strong><span style="font-size: 18px;">费用包含</span></strong></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">基础服务费：基础服务费、基础服务费、基础服务费、基础服务费、基础服务费。</span></p></li><li><p><span style="font-size: 14px;">附加费用：附加费用、<span style="font-size: 18px;">附加费用、附加费用、附加费用、附加费用。</span></span></p><p><span style="font-size: 18px;"></span></p></li></ul><p><span style="font-size: 18px;"><span style="font-size: 18px;"></span><strong>费用不包含</strong></span></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">未提及费用</span><span style="font-size: 18px;"><br/></span></p></li></ul>
         * product_description : <p style="text-align: center;"><span style="font-size: 30px;">第一天</span></p><p><span style="font-size: 20px;">线路摘要&nbsp;&nbsp;&nbsp;&nbsp;</span>体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点体验亮点体验亮</p><p></br</p><p style="text-align: center;"><span style="font-size: 20px;">第一站</span></br></p><p style="text-align: center;"><img src="http://img.shahaizhi.com/%28null%291530254045" width="100%" height="293"></p>
         * origin_name : 初始地
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
        private String product_description;
        private String origin_name;
        private String price_comment;
        private ReviewDataBean review_data;
        private GuideReviewDataBean guide_review_data;

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

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
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

        public ReviewDataBean getReview_data() {
            return review_data;
        }

        public void setReview_data(ReviewDataBean review_data) {
            this.review_data = review_data;
        }

        public GuideReviewDataBean getGuide_review_data() {
            return guide_review_data;
        }

        public void setGuide_review_data(GuideReviewDataBean guide_review_data) {
            this.guide_review_data = guide_review_data;
        }
    }
}
