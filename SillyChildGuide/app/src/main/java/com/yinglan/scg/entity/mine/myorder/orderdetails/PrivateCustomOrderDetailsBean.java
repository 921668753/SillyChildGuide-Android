package com.yinglan.scg.entity.mine.myorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class PrivateCustomOrderDetailsBean extends BaseResult<PrivateCustomOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * stay_preference : 住宿1
         * order_number : SHZ20180904120014723654307
         * guide_review_data : {"content":"太累了","pictures":["http://img.shahaizhi.com/FgdCCAy-2gt-Jh9_GwK7kZvB6_7t"]}
         * end_time : 1537545600
         * travel_preference : 出行1
         * destination_name : 行程结束地
         * order_price : 0.01
         * title : gg定制游
         * connect_number : 17051335257
         * start_time : 1537372800
         * schedule : <p>这个是行程安排</p>
         * order_status : 4
         * license_plate : A123
         * model_name : 途观
         * booking_request : 3成人1行李
         * review_data : {"nickname":"天若有情x","face":"http://img.shahaizhi.com/FuIb5rJYd2wozXtjE-ma507JRaOp","content":"图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张图片测试4张","picture":["http://img.shahaizhi.com/Fo73lizwItYpAp4U5wA7UTMD5ae1","http://img.shahaizhi.com/FsfMvWNBvC3V3cO2WWeJQpKnx2Ie"],"satisfaction_level":5,"create_time":"1535619416"}
         * subtitle : 3人1行李
         * contact : 阿柯
         * repast_preference : 餐饮1
         * book_comment : <p>这个是订购说明</p>
         * origin_name : 行程开始出发地
         * price_comment : <p><strong><span style="font-size: 18px;">费用包含</span></strong></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">基础服务费：基础服务费、基础服务费、基础服务费、基础服务费、基础服务费。</span></p></li><li><p><span style="font-size: 14px;">附加费用：附加费用、<span style="font-size: 18px;">附加费用、附加费用、附加费用、附加费用。</span></span></p><p><span style="font-size: 18px;"></span></p></li></ul><p><span style="font-size: 18px;"><span style="font-size: 18px;"></span><strong>费用不包含</strong></span></p><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p><span style="font-size: 14px;">未提及费用</span><span style="font-size: 18px;"><br/></span></p></li></ul>
         */

        private String stay_preference;
        private String order_number;
        private GuideReviewDataBean guide_review_data;
        private String end_time;
        private String travel_preference;
        private String destination_name;
        private String order_price;
        private String title;
        private String connect_number;
        private String start_time;
        private String schedule;
        private int order_status;
        private String license_plate;
        private String model_name;
        private String booking_request;
        private ReviewDataBean review_data;
        private String subtitle;
        private String contact;
        private String repast_preference;
        private String book_comment;
        private String origin_name;
        private String price_comment;

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

        public GuideReviewDataBean getGuide_review_data() {
            return guide_review_data;
        }

        public void setGuide_review_data(GuideReviewDataBean guide_review_data) {
            this.guide_review_data = guide_review_data;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTravel_preference() {
            return travel_preference;
        }

        public void setTravel_preference(String travel_preference) {
            this.travel_preference = travel_preference;
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

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
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

        public ReviewDataBean getReview_data() {
            return review_data;
        }

        public void setReview_data(ReviewDataBean review_data) {
            this.review_data = review_data;
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

        public String getRepast_preference() {
            return repast_preference;
        }

        public void setRepast_preference(String repast_preference) {
            this.repast_preference = repast_preference;
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

        public class GuideReviewDataBean {
            /**
             * content : 太累了
             * pictures : ["http://img.shahaizhi.com/FgdCCAy-2gt-Jh9_GwK7kZvB6_7t"]
             */

            private String content;
            private List<String> pictures;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<String> getPictures() {
                return pictures;
            }

            public void setPictures(List<String> pictures) {
                this.pictures = pictures;
            }
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
