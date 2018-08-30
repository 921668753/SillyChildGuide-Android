package com.yinglan.scg.entity.mine.personaldata;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class PersonalDataBean extends BaseResult<PersonalDataBean.DataBean> {


    public class DataBean {
        /**
         * face :
         * sex : 0
         * nickname : 17051335257
         * remark :
         * photo : [{"silde_id":26,"store_id":6,"silde_url":"","img":"fs:/images/s_side.jpg","sildeImg":""}]
         */

        private String face;
        private int sex;
        private String nickname;
        private String remark;
        private List<PhotoBean> photo;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<PhotoBean> getPhoto() {
            return photo;
        }

        public void setPhoto(List<PhotoBean> photo) {
            this.photo = photo;
        }

        public class PhotoBean {
            /**
             * silde_id : 26
             * store_id : 6
             * silde_url :
             * img : fs:/images/s_side.jpg
             * sildeImg :
             */

            private int silde_id;
            private int store_id;
            private String silde_url;
            private String img;
            private String sildeImg;

            public int getSilde_id() {
                return silde_id;
            }

            public void setSilde_id(int silde_id) {
                this.silde_id = silde_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getSilde_url() {
                return silde_url;
            }

            public void setSilde_url(String silde_url) {
                this.silde_url = silde_url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getSildeImg() {
                return sildeImg;
            }

            public void setSildeImg(String sildeImg) {
                this.sildeImg = sildeImg;
            }
        }
    }
}
