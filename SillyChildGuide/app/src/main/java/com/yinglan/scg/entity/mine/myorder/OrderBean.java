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
         * currentPageNo : 1
         * draw : 0
         * pageSize : 5
         * result : [{"order_id":152,"order_number":"SHZ20180824164236428865589","product_set_cd":3,"product_set_name":"包车","pay_status":1,"status":1,"main_picture":"http://img.shahaizhi.com/%28null%291530269095","service_start_time":1542211200,"service_end_time":1543420800,"title":"东京室内一日包车","phone":"18550875927","rong_id":"G14","order_amount":"0.15","pay_amount":"0.15","dis_amount":"0.00","service_director":"傻孩志"},{"order_id":151,"order_number":"SHZ20180824164119700999259","product_set_cd":2,"product_set_name":"送机","pay_status":1,"status":1,"main_picture":"http://img.shahaizhi.com/%28null%291530269095","service_start_time":"1535272849","title":"成田机场送机","phone":"18550875927","rong_id":"G14","order_amount":"0.01","pay_amount":"0.01","dis_amount":"0.00","service_director":"傻孩志"},{"order_id":150,"order_number":"SHZ20180824164009445013613","product_set_cd":1,"product_set_name":"接机","pay_status":1,"status":1,"main_picture":"http://img.shahaizhi.com/%28null%291530269095","service_start_time":"1535186362","title":"成田机场接机","phone":"18550875927","rong_id":"G14","order_amount":"0.01","pay_amount":"0.01","dis_amount":"0.00","service_director":"傻孩志"}]
         * totalCount : 3
         * totalPageCount : 1
         */

        private int currentPageNo;
        private int draw;
        private int pageSize;
        private int totalCount;
        private int totalPageCount;
        @SerializedName("result")
        private List<ResultBean> resultX;

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
             * order_id : 152
             * order_number : SHZ20180824164236428865589
             * product_set_cd : 3
             * product_set_name : 包车
             * pay_status : 1
             * status : 1
             * main_picture : http://img.shahaizhi.com/%28null%291530269095
             * service_start_time : 1542211200
             * service_end_time : 1543420800
             * title : 东京室内一日包车
             * phone : 18550875927
             * rong_id : G14
             * order_amount : 0.15
             * pay_amount : 0.15
             * dis_amount : 0.00
             * service_director : 傻孩志
             */

            private int order_id;
            private String order_number;
            private int product_set_cd;
            private String product_set_name;
            private int pay_status;
            private int status;
            private String main_picture;
            private String service_start_time;
            private String service_end_time;
            private String title;
            private String phone;
            private String rong_id;
            private String order_amount;
            private String start_time;
            private String end_time;
            private String pay_amount;
            private String dis_amount;
            private String service_director;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public int getProduct_set_cd() {
                return product_set_cd;
            }

            public void setProduct_set_cd(int product_set_cd) {
                this.product_set_cd = product_set_cd;
            }

            public String getProduct_set_name() {
                return product_set_name;
            }

            public void setProduct_set_name(String product_set_name) {
                this.product_set_name = product_set_name;
            }

            public int getPay_status() {
                return pay_status;
            }

            public void setPay_status(int pay_status) {
                this.pay_status = pay_status;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getMain_picture() {
                return main_picture;
            }

            public void setMain_picture(String main_picture) {
                this.main_picture = main_picture;
            }

            public String getService_start_time() {
                return service_start_time;
            }

            public void setService_start_time(String service_start_time) {
                this.service_start_time = service_start_time;
            }

            public String getService_end_time() {
                return service_end_time;
            }

            public void setService_end_time(String service_end_time) {
                this.service_end_time = service_end_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRong_id() {
                return rong_id;
            }

            public void setRong_id(String rong_id) {
                this.rong_id = rong_id;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public String getDis_amount() {
                return dis_amount;
            }

            public void setDis_amount(String dis_amount) {
                this.dis_amount = dis_amount;
            }

            public String getService_director() {
                return service_director;
            }

            public void setService_director(String service_director) {
                this.service_director = service_director;
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
        }
    }
}