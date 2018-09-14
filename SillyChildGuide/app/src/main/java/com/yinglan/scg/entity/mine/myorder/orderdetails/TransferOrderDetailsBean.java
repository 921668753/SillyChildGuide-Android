package com.yinglan.scg.entity.mine.myorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class TransferOrderDetailsBean extends BaseResult<TransferOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * order_number : SHZ2018082518191295203625
         * destination_name : 放鹤路1088号
         * order_price : 0.01
         * title : 接机
         * connect_number : 15515916027
         * start_time : 1535685943
         * order_status : 1
         * license_plate : A123
         * model_name : 途观
         * booking_request : 3成人1行李
         * subtitle : 3人1行李
         * contact : 彪哥
         * origin_name : 成田机场
         * price_comment : <p><strong><span style="font-size: 18px;">费用包含</span></strong></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">基础服务费：基础服务费、基础服务费、基础服务费、基础服务费、基础服务费。</span></p></li><li><p><span style="font-size: 14px;">附加费用：附加费用、<span style="font-size: 18px;">附加费用、附加费用、附加费用、附加费用。</span></span></p><p><span style="font-size: 18px;"></span></p></li></ul><p><span style="font-size: 18px;"><span style="font-size: 18px;"></span><strong>费用不包含</strong></span></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">未提及费用</span><span style="font-size: 18px;"><br/></span></p></li></ul>
         */

        private String order_number;
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
        private String origin_name;
        private String price_comment;
        private ReviewDataBean review_data;

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
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

        public class ReviewDataBean {
            /**
             * nickname : 天若有情x
             * face : http://img.shahaizhi.com/FuIb5rJYd2wozXtjE-ma507JRaOp
             * content : 图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张
             * picture : ["http://img.shahaizhi.com/Fo73lizwItYpAp4U5wA7UTMD5ae1","http://img.shahaizhi.com/FsfMvWNBvC3V3cO2WWeJQpKnx2Ie"]
             * satisfaction_level : 5
             * create_time : 1535619416
             */

            private String nickname;
            private String face;
            private String content;
            private int satisfaction_level;
            private String create_time;
            private List<String> picture;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getSatisfaction_level() {
                return satisfaction_level;
            }

            public void setSatisfaction_level(int satisfaction_level) {
                this.satisfaction_level = satisfaction_level;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public List<String> getPicture() {
                return picture;
            }

            public void setPicture(List<String> picture) {
                this.picture = picture;
            }
        }
    }
}
