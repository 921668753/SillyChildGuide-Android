package com.yinglan.scg.entity.mine.myorder.orderdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class TransferOrderDetailsBean extends BaseResult<TransferOrderDetailsBean.DataBean> {


    public class DataBean {
        /**
         * model_list : [{"id":2,"license_plate":"豫G77777","model_name":"迈腾","is_default":0},{"id":4,"license_plate":"京A777777","model_name":"帕萨特","is_default":1}]
         * start_time : 1536134000
         * booking_request : 2成人1行李
         * order_number : SHZ20180903155402787087264
         * subtitle : 2人1行李
         * destination_name : gg
         * order_price : 0.01
         * title : 接机
         * origin_name : 成田机场
         * price_comment : {"title":"不支持退订","content":"<p style=\"font-size:14px;color:green;\">&nbsp;&nbsp;&nbsp;&nbsp;这个是简短的退订政策，该订单不支持退订，请与服务人员了解详情<\/p>"}
         */

        private String start_time;
        private String booking_request;
        private String order_number;
        private String subtitle;
        private String destination_name;
        private String order_price;
        private String title;
        private String origin_name;
        private String price_comment;
        private List<ModelListBean> model_list;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getBooking_request() {
            return booking_request;
        }

        public void setBooking_request(String booking_request) {
            this.booking_request = booking_request;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
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


        public class ModelListBean {
            /**
             * id : 2
             * license_plate : 豫G77777
             * model_name : 迈腾
             * is_default : 0
             */

            private int id;
            private String license_plate;
            private String model_name;
            private int is_default;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getIs_default() {
                return is_default;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }
        }
    }
}
