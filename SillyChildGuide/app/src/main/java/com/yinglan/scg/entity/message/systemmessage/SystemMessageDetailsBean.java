package com.yinglan.scg.entity.message.systemmessage;

import com.common.cklibrary.entity.BaseResult;

public class SystemMessageDetailsBean extends BaseResult<SystemMessageDetailsBean.DataBean> {


    public class DataBean {
        /**
         * news_id : 14
         * member_id : 30
         * news_title : 系统消息
         * news_text : 傻孩子测试消息
         * push_tima : null
         * is_read : 1
         * news_subject : 测试主题8
         * news_img : http://ovwiqces1.bkt.clouddn.com/SHZS_29_IMG_20180528_193922.jpg
         */

        private int news_id;
        private int member_id;
        private String news_title;
        private String news_text;
        private String push_tima;
        private int is_read;
        private String news_subject;
        private String news_img;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
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

        public String getPush_tima() {
            return push_tima;
        }

        public void setPush_tima(String push_tima) {
            this.push_tima = push_tima;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }

        public String getNews_subject() {
            return news_subject;
        }

        public void setNews_subject(String news_subject) {
            this.news_subject = news_subject;
        }

        public String getNews_img() {
            return news_img;
        }

        public void setNews_img(String news_img) {
            this.news_img = news_img;
        }
    }
}
