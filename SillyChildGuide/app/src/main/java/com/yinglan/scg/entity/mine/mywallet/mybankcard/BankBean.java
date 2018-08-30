package com.yinglan.scg.entity.mine.mywallet.mybankcard;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class BankBean extends BaseResult<List<BankBean.DataBean>> {


    public class DataBean implements IPickerViewData {
        /**
         * id : 1
         * name : 中国人民银行
         * code : PBOCCNBJ
         * is_enable : 1
         * is_deleted : 0
         * creator_id : 1
         * modifier_id : null
         * create_time : 1521603604000
         * modify_time : null
         */

        private int id;
        private String name;
        private String code;
        private int is_enable;
        private int is_deleted;
        private int creator_id;
        private String modifier_id;
        private long create_time;
        private String modify_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getIs_enable() {
            return is_enable;
        }

        public void setIs_enable(int is_enable) {
            this.is_enable = is_enable;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public int getCreator_id() {
            return creator_id;
        }

        public void setCreator_id(int creator_id) {
            this.creator_id = creator_id;
        }

        public String getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(String modifier_id) {
            this.modifier_id = modifier_id;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getModify_time() {
            return modify_time;
        }

        public void setModify_time(String modify_time) {
            this.modify_time = modify_time;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
