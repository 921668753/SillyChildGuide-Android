package com.yinglan.scg.entity.mine.mywallet;

import com.common.cklibrary.entity.BaseResult;

public class MyWalletBean extends BaseResult<MyWalletBean.DataBean> {


    public class DataBean {
        /**
         * id : 3
         * member_id : 29
         * balance : 495
         * freeze_amount : 0
         * bank : 中国工商银行
         * open_bank : 中国工商银行
         * account_no : 12345678912
         * account_name : 啦啦啦
         * is_deleted : false
         * phone : 17051333525
         * is_default : 1
         * id_number : 111111111111111111
         * fee : 5
         * get_time : 5
         * bank_id : 21
         */

        private int id;
        private int member_id;
        private String balance;
        private String freeze_amount;
        private String bank;
        private String open_bank;
        private String account_no;
        private String account_name;
        private boolean is_deleted;
        private String phone;
        private int is_default;
        private String id_number;
        private String fee;
        private String get_time;
        private int bank_id;

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

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFreeze_amount() {
            return freeze_amount;
        }

        public void setFreeze_amount(String freeze_amount) {
            this.freeze_amount = freeze_amount;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getOpen_bank() {
            return open_bank;
        }

        public void setOpen_bank(String open_bank) {
            this.open_bank = open_bank;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public boolean isIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(boolean is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getGet_time() {
            return get_time;
        }

        public void setGet_time(String get_time) {
            this.get_time = get_time;
        }

        public int getBank_id() {
            return bank_id;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }
    }
}
