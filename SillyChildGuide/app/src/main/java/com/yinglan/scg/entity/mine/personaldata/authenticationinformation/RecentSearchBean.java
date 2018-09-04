package com.yinglan.scg.entity.mine.personaldata.authenticationinformation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class RecentSearchBean extends BaseResult<List<RecentSearchBean.DataBean>> {

    public static class DataBean {
        /**
         * name : 休闲零食
         * type : 0  找人   1  找文章
         */
        private String name;
        private int type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}
