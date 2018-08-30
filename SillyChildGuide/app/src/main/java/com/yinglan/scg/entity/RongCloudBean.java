package com.yinglan.scg.entity;

import com.common.cklibrary.entity.BaseResult;

public class RongCloudBean extends BaseResult<RongCloudBean.DataBean> {

    public class DataBean {
        /**
         * authToken : dmrMa7omgEbJGmsv7vmRtg_g3zt1GjkpiIkWvtkW:IZQIWZ1FAMd3MJrGZsVTmxoeaiw=:eyJpbnNlcnRPbmx5IjowLCJzY29wZSI6InNoYWhhaXppIiwicmV0dXJuQm9keSI6IntcIm5hbWVcIjogJChrZXkpLCBcIm9yaWdpbk5hbWVcIiA6ICQoZm5hbWUpLCBcIndpZHRoXCI6ICQoaW1hZ2VJbmZvLndpZHRoKSwgXCJoZWlnaHRcIjogJChpbWFnZUluZm8uaGVpZ2h0KSwgXCJzaXplXCI6ICQoZnNpemUpLCBcImluZGV4XCI6ICQoeDppbmRleCl9IiwiZnNpemVMaW1pdCI6MjA5NzE1MjAwLCJkZWFkbGluZSI6MTUyNzM2NTExNH0=
         * host : http://ovwiqces1.bkt.clouddn.com/
         */

        private String face;
        private String nickname;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }


}
