package com.yinglan.scg.entity.service.dialog;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class TripBean extends BaseResult<List<TripBean.DataBean>> {


    public class DataBean {
        /**
         * start_time : 1535644800
         * product_set_name : 精品线路
         * end_time : 1537372800
         * title : 1人0行李
         * product_set_cd : 5
         */

        private String start_time;
        private String product_set_name;
        private String end_time;
        private String title;
        private int product_set_cd;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getProduct_set_name() {
            return product_set_name;
        }

        public void setProduct_set_name(String product_set_name) {
            this.product_set_name = product_set_name;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getProduct_set_cd() {
            return product_set_cd;
        }

        public void setProduct_set_cd(int product_set_cd) {
            this.product_set_cd = product_set_cd;
        }
    }
}
