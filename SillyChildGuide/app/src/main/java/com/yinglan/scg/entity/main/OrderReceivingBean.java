package com.yinglan.scg.entity.main;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderReceivingBean extends BaseResult<OrderReceivingBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 5
         * totalCount : 35
         * currentPageNo : 1
         * draw : 0
         * result : [{"order_number":"SHZ20180910123749863894833","product_set_name":"精品线路","product_set_cd":5,"start_time":"1537459200","end_time":1537718400,"product_name":"大阪3天2夜精选路线","order_price":"0.02"},{"order_number":"SHZ20180903155402787087264","product_set_name":"接机","product_set_cd":1,"start_time":"1536134000","origin_name":"成田机场","destination_name":"gg","order_price":"0.01"},{"order_number":"SHZ20180903155604889329092","product_set_name":"送机","product_set_cd":2,"start_time":"1536047741","origin_name":"成田机场","destination_name":"cgf","order_price":"0.01"},{"order_number":"SHZ2018090316002280595258","product_set_name":"精品线路","product_set_cd":5,"start_time":"1536768000","end_time":1537027200,"product_name":"大阪3天2夜精选路线","order_price":"0.01"},{"order_number":"SHZ2018082413530409100405","product_set_name":"包车","product_set_cd":3,"start_time":"1535904000","end_time":"1536163200","product_name":"东京市内包车","order_price":"0.03"}]
         * totalPageCount : 7
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        @SerializedName("result")
        private List<ResultBean> resultX;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * order_number : SHZ20180910123749863894833
             * product_set_name : 精品线路
             * product_set_cd : 5
             * start_time : 1537459200
             * end_time : 1537718400
             * product_name : 大阪3天2夜精选路线
             * order_price : 0.02
             * origin_name : 成田机场
             * destination_name : gg
             */

            private String order_number;
            private String product_set_name;
            private int product_set_cd;
            private String start_time;
            private String end_time;
            private String product_name;
            private String order_price;
            private String origin_name;
            private String destination_name;

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getProduct_set_name() {
                return product_set_name;
            }

            public void setProduct_set_name(String product_set_name) {
                this.product_set_name = product_set_name;
            }

            public int getProduct_set_cd() {
                return product_set_cd;
            }

            public void setProduct_set_cd(int product_set_cd) {
                this.product_set_cd = product_set_cd;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public String getOrigin_name() {
                return origin_name;
            }

            public void setOrigin_name(String origin_name) {
                this.origin_name = origin_name;
            }

            public String getDestination_name() {
                return destination_name;
            }

            public void setDestination_name(String destination_name) {
                this.destination_name = destination_name;
            }
        }
    }
}
