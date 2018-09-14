package com.yinglan.scg.entity.mine.myorder;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ForServiceBean extends BaseResult<ForServiceBean.DataBean> {


    public class DataBean {
        private List<WaitingListBean> waiting_list;
        private List<ProcessingListBean> processing_list;

        public List<WaitingListBean> getWaiting_list() {
            return waiting_list;
        }

        public void setWaiting_list(List<WaitingListBean> waiting_list) {
            this.waiting_list = waiting_list;
        }

        public List<ProcessingListBean> getProcessing_list() {
            return processing_list;
        }

        public void setProcessing_list(List<ProcessingListBean> processing_list) {
            this.processing_list = processing_list;
        }

        public class WaitingListBean {
            /**
             * order_number : SHZ2018082518191295203625
             * product_set_name : 接机
             * product_set_cd : 1
             * order_price : 0.01
             * status : 1
             * start_time : 1538785943
             * origin_name : 成田机场
             * destination_name : 放鹤路1088号
             * order_status : 2
             */

            private String order_number;
            private String product_set_name;
            private int product_set_cd;
            private String order_price;
            private int status;
            private String end_time;
            private String start_time;
            private String origin_name;
            private String product_name;
            private String destination_name;
            private int order_status;
            private int isFirst;

            public int getIsFirst() {
                return isFirst;
            }

            public void setIsFirst(int isFirst) {
                this.isFirst = isFirst;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
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

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }
        }

        public class ProcessingListBean {
            /**
             * order_number : SHZ20180827094535332869911
             * product_set_name : 送机
             * product_set_cd : 2
             * order_price : 0.01
             * status : 1
             * start_time : 1535685943
             * origin_name : 成田机场
             * destination_name : 放鹤路1088号
             * order_status : 1
             * end_time : 1537163200
             * product_name : 东京市内包车
             */

            private String order_number;
            private String product_set_name;
            private int product_set_cd;
            private String order_price;
            private int status;
            private int isFirst;
            private String end_time;
            private String start_time;
            private String origin_name;
            private String product_name;
            private String destination_name;
            private int order_status;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getOrigin_name() {
                return origin_name;
            }

            public void setOrigin_name(String origin_name) {
                this.origin_name = origin_name;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getDestination_name() {
                return destination_name;
            }

            public void setDestination_name(String destination_name) {
                this.destination_name = destination_name;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }
        }
    }
}
