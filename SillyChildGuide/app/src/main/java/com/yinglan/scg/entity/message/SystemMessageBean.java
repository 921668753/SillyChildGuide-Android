package com.yinglan.scg.entity.message;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SystemMessageBean extends BaseResult<List<SystemMessageBean.DataBean>> {


    public class DataBean {
        /**
         * news_id : 9
         * news_title : 系统消息
         * news_text : 傻孩子测试消息
         * lastTime : 2018-05-29 00:00:00.0
         * num : 1
         * title_img : http://ovwiqces1.bkt.clouddn.com/SHZS_29_IMG_20180528_193922.jpg
         */

        private int news_id;
        private String news_title;
        private String news_text;
        private String lastTime;
        private int num;
        private String title_img;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public String getNews_text() {
            return news_text;
        }

        public void setNews_text(String news_text) {
            this.news_text = news_text;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTitle_img() {
            return title_img;
        }

        public void setTitle_img(String title_img) {
            this.title_img = title_img;
        }
    }
}
