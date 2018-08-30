package com.yinglan.scg.entity.mine.mywallet.accountdetails;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 2017/7/27.
 */

public class AccountDetailsBean extends BaseResult<AccountDetailsBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 20
         * totalCount : 5
         * currentPageNo : 1
         * draw : 0
         * totalPageCount : 1
         * result : [{"id":16,"member_id":52,"type":"3","amount":180.99,"create_time":1528437600000,"status":0,"fee_amount":0,"real_amount":0},{"id":19,"member_id":52,"type":"2","amount":0.01,"create_time":1528444800000,"status":0,"fee_amount":0,"real_amount":0.01},{"id":20,"member_id":52,"type":"2","amount":0.01,"create_time":1528444800000,"status":0,"fee_amount":0,"real_amount":0.01},{"id":21,"member_id":52,"type":"2","amount":0.01,"create_time":1528444800000,"status":0,"fee_amount":0,"real_amount":0.01},{"id":22,"member_id":52,"type":"1","amount":100,"create_time":1528444800000,"status":0,"fee_amount":0,"real_amount":0.01}]
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
             * id : 16
             * member_id : 52
             * type : 3
             * amount : 180.99
             * create_time : 1528437600000
             * status : 0
             * fee_amount : 0
             * real_amount : 0
             */

            private int id;
            private int member_id;
            private int type;
            private String amount;
            private String create_time;
            private int status;
            private String fee_amount;
            private String real_amount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getFee_amount() {
                return fee_amount;
            }

            public void setFee_amount(String fee_amount) {
                this.fee_amount = fee_amount;
            }

            public String getReal_amount() {
                return real_amount;
            }

            public void setReal_amount(String real_amount) {
                this.real_amount = real_amount;
            }
        }
    }
}
