package com.yinglan.scg.entity.mine.myorder;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2018/8/17.
 */

public class OrderBean extends BaseResult<OrderBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 5
         * totalCount : 5
         * currentPageNo : 1
         * draw : 0
         * result : [{"order_number":"SHZ20180904120014723654307","product_set_name":"私人定制","product_set_cd":4,"order_price":"0.01","product_name":"gg定制游","start_time":1537372800,"end_time":1537545600,"order_status":2},{"order_number":"SHZ2018082518191295203625","product_set_name":"接机","product_set_cd":1,"order_price":"0.01","start_time":1535685943,"order_status":1,"origin_name":"成田机场","destination_name":"放鹤路1088号"},{"order_number":"SHZ20180827094535332869911","product_set_name":"送机","product_set_cd":2,"order_price":"0.01","start_time":1535685943,"order_status":1,"origin_name":"成田机场","destination_name":"放鹤路1088号"},{"order_number":"SHZ2018082712375753977508","product_set_name":"包车","product_set_cd":3,"order_price":"0.04","start_time":1535904000,"end_time":1537163200,"order_status":1,"product_name":"东京市内包车"},{"order_number":"SHZ20180829095839568812511","product_set_name":"精品线路","product_set_cd":5,"order_price":"0.01","start_time":1535644800,"end_time":1537372800,"order_status":1,"product_name":"大阪3天2夜精选路线"}]
         * totalPageCount : 1
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
             * order_number : SHZ20180904120014723654307
             * product_set_name : 私人定制
             * product_set_cd : 4
             * order_price : 0.01
             * product_name : gg定制游
             * start_time : 1537372800
             * end_time : 1537545600
             * order_status : 2
             * origin_name : 成田机场
             * destination_name : 放鹤路1088号
             */

            private String order_number;
            private String product_set_name;
            private int product_set_cd;
            private String order_price;
            private int isFirst;
            private String product_name;
            private String start_time;
            private String end_time;
            private int order_status;
            private String origin_name;
            private String destination_name;

            public int getIsFirst() {
                return isFirst;
            }

            public void setIsFirst(int isFirst) {
                this.isFirst = isFirst;
            }

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

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
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

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
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