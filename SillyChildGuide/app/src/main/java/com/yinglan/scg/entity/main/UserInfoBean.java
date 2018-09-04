package com.yinglan.scg.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;

/**
 * 用户信息
 * Created by Administrator on 2017/9/7.
 */

public class UserInfoBean extends BaseResult<UserInfoBean.DataBean> implements Serializable {


    public class DataBean {
        /**
         * birthday : 1527772195
         * shz : shz37
         * city : 东城区
         * signature : null
         * level_id : 1
         * collectNum : 0
         * point : 60
         * province : 北京
         * tel : null
         * zip : null
         * address : null
         * mp : 60
         * returnedOrderCount : 0
         * level : 普通会员
         * sex : 1
         * region_id : 2821
         * mobile : 18550875927
         * commentOrderCount : 0
         * face :
         * province_id : 1
         * nick_name : 18550875927
         * name : null
         * shippingOrderCount : 0
         * favoriteStoreCount : 0
         * invite_code : null
         * paymentOrderCount : 0
         * region : 内环到三环里
         * username : 18550875927
         * city_id : 2802
         * favoriteCount : 3
         */

        private String birthday;
        private String shz;
        private String city;
        private String signature;
        private int level_id;
        private int collectNum;
        private int point;
        private String province;
        private String tel;
        private String zip;
        private String address;
        private int mp;
        private int returnedOrderCount;
        private String level;
        private int sex;
        private int region_id;
        private String mobile;
        private int commentOrderCount;
        private String face;
        private int province_id;
        private String nick_name;
        private String name;
        private int shippingOrderCount;
        private int favoriteStoreCount;
        private String invite_code;
        private int paymentOrderCount;
        private String region;
        private String username;
        private int city_id;
        private int favoriteCount;
        private String collected_number;
        private String concern_number;
        private String fans_number;
        private String like_number;


        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getShz() {
            return shz;
        }

        public void setShz(String shz) {
            this.shz = shz;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getLevel_id() {
            return level_id;
        }

        public void setLevel_id(int level_id) {
            this.level_id = level_id;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getMp() {
            return mp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }

        public int getReturnedOrderCount() {
            return returnedOrderCount;
        }

        public void setReturnedOrderCount(int returnedOrderCount) {
            this.returnedOrderCount = returnedOrderCount;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getCommentOrderCount() {
            return commentOrderCount;
        }

        public void setCommentOrderCount(int commentOrderCount) {
            this.commentOrderCount = commentOrderCount;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getShippingOrderCount() {
            return shippingOrderCount;
        }

        public void setShippingOrderCount(int shippingOrderCount) {
            this.shippingOrderCount = shippingOrderCount;
        }

        public int getFavoriteStoreCount() {
            return favoriteStoreCount;
        }

        public void setFavoriteStoreCount(int favoriteStoreCount) {
            this.favoriteStoreCount = favoriteStoreCount;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public int getPaymentOrderCount() {
            return paymentOrderCount;
        }

        public void setPaymentOrderCount(int paymentOrderCount) {
            this.paymentOrderCount = paymentOrderCount;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public String getCollected_number() {
            return collected_number;
        }

        public void setCollected_number(String collected_number) {
            this.collected_number = collected_number;
        }

        public String getConcern_number() {
            return concern_number;
        }

        public void setConcern_number(String concern_number) {
            this.concern_number = concern_number;
        }

        public String getFans_number() {
            return fans_number;
        }

        public void setFans_number(String fans_number) {
            this.fans_number = fans_number;
        }

        public String getLike_number() {
            return like_number;
        }

        public void setLike_number(String like_number) {
            this.like_number = like_number;
        }
    }
}
