package com.yinglan.scg.entity.orderreceiving.dialog;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class UnwillingnessTakeOrdersBean extends BaseResult<List<UnwillingnessTakeOrdersBean.DataBean>> {


    public class DataBean {
        /**
         * id : 1
         * reason : 时间不合适，无法服务此单
         */

        private int id;
        private String reason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
