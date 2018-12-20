package com.zsbatech.baasKettleManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertUpdateStepExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InsertUpdateStepExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdIsNull() {
            addCriterion("trans_meta_id is null");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdIsNotNull() {
            addCriterion("trans_meta_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdEqualTo(Integer value) {
            addCriterion("trans_meta_id =", value, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdNotEqualTo(Integer value) {
            addCriterion("trans_meta_id <>", value, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdGreaterThan(Integer value) {
            addCriterion("trans_meta_id >", value, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trans_meta_id >=", value, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdLessThan(Integer value) {
            addCriterion("trans_meta_id <", value, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdLessThanOrEqualTo(Integer value) {
            addCriterion("trans_meta_id <=", value, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdIn(List<Integer> values) {
            addCriterion("trans_meta_id in", values, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdNotIn(List<Integer> values) {
            addCriterion("trans_meta_id not in", values, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdBetween(Integer value1, Integer value2) {
            addCriterion("trans_meta_id between", value1, value2, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andTransMetaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trans_meta_id not between", value1, value2, "transMetaId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdIsNull() {
            addCriterion("db_management_id is null");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdIsNotNull() {
            addCriterion("db_management_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdEqualTo(Integer value) {
            addCriterion("db_management_id =", value, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdNotEqualTo(Integer value) {
            addCriterion("db_management_id <>", value, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdGreaterThan(Integer value) {
            addCriterion("db_management_id >", value, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("db_management_id >=", value, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdLessThan(Integer value) {
            addCriterion("db_management_id <", value, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdLessThanOrEqualTo(Integer value) {
            addCriterion("db_management_id <=", value, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdIn(List<Integer> values) {
            addCriterion("db_management_id in", values, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdNotIn(List<Integer> values) {
            addCriterion("db_management_id not in", values, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdBetween(Integer value1, Integer value2) {
            addCriterion("db_management_id between", value1, value2, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andDbManagementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("db_management_id not between", value1, value2, "dbManagementId");
            return (Criteria) this;
        }

        public Criteria andStepNameIsNull() {
            addCriterion("step_name is null");
            return (Criteria) this;
        }

        public Criteria andStepNameIsNotNull() {
            addCriterion("step_name is not null");
            return (Criteria) this;
        }

        public Criteria andStepNameEqualTo(String value) {
            addCriterion("step_name =", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotEqualTo(String value) {
            addCriterion("step_name <>", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameGreaterThan(String value) {
            addCriterion("step_name >", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameGreaterThanOrEqualTo(String value) {
            addCriterion("step_name >=", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameLessThan(String value) {
            addCriterion("step_name <", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameLessThanOrEqualTo(String value) {
            addCriterion("step_name <=", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameLike(String value) {
            addCriterion("step_name like", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotLike(String value) {
            addCriterion("step_name not like", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameIn(List<String> values) {
            addCriterion("step_name in", values, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotIn(List<String> values) {
            addCriterion("step_name not in", values, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameBetween(String value1, String value2) {
            addCriterion("step_name between", value1, value2, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotBetween(String value1, String value2) {
            addCriterion("step_name not between", value1, value2, "stepName");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupIsNull() {
            addCriterion("update_lookup is null");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupIsNotNull() {
            addCriterion("update_lookup is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupEqualTo(String value) {
            addCriterion("update_lookup =", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupNotEqualTo(String value) {
            addCriterion("update_lookup <>", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupGreaterThan(String value) {
            addCriterion("update_lookup >", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupGreaterThanOrEqualTo(String value) {
            addCriterion("update_lookup >=", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupLessThan(String value) {
            addCriterion("update_lookup <", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupLessThanOrEqualTo(String value) {
            addCriterion("update_lookup <=", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupLike(String value) {
            addCriterion("update_lookup like", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupNotLike(String value) {
            addCriterion("update_lookup not like", value, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupIn(List<String> values) {
            addCriterion("update_lookup in", values, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupNotIn(List<String> values) {
            addCriterion("update_lookup not in", values, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupBetween(String value1, String value2) {
            addCriterion("update_lookup between", value1, value2, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andUpdateLookupNotBetween(String value1, String value2) {
            addCriterion("update_lookup not between", value1, value2, "updateLookup");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnIsNull() {
            addCriterion("time_stamp_column is null");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnIsNotNull() {
            addCriterion("time_stamp_column is not null");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnEqualTo(Date value) {
            addCriterion("time_stamp_column =", value, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnNotEqualTo(Date value) {
            addCriterion("time_stamp_column <>", value, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnGreaterThan(Date value) {
            addCriterion("time_stamp_column >", value, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnGreaterThanOrEqualTo(Date value) {
            addCriterion("time_stamp_column >=", value, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnLessThan(Date value) {
            addCriterion("time_stamp_column <", value, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnLessThanOrEqualTo(Date value) {
            addCriterion("time_stamp_column <=", value, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnIn(List<Date> values) {
            addCriterion("time_stamp_column in", values, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnNotIn(List<Date> values) {
            addCriterion("time_stamp_column not in", values, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnBetween(Date value1, Date value2) {
            addCriterion("time_stamp_column between", value1, value2, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andTimeStampColumnNotBetween(Date value1, Date value2) {
            addCriterion("time_stamp_column not between", value1, value2, "timeStampColumn");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andKeyLookupIsNull() {
            addCriterion("key_lookup is null");
            return (Criteria) this;
        }

        public Criteria andKeyLookupIsNotNull() {
            addCriterion("key_lookup is not null");
            return (Criteria) this;
        }

        public Criteria andKeyLookupEqualTo(String value) {
            addCriterion("key_lookup =", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupNotEqualTo(String value) {
            addCriterion("key_lookup <>", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupGreaterThan(String value) {
            addCriterion("key_lookup >", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupGreaterThanOrEqualTo(String value) {
            addCriterion("key_lookup >=", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupLessThan(String value) {
            addCriterion("key_lookup <", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupLessThanOrEqualTo(String value) {
            addCriterion("key_lookup <=", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupLike(String value) {
            addCriterion("key_lookup like", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupNotLike(String value) {
            addCriterion("key_lookup not like", value, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupIn(List<String> values) {
            addCriterion("key_lookup in", values, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupNotIn(List<String> values) {
            addCriterion("key_lookup not in", values, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupBetween(String value1, String value2) {
            addCriterion("key_lookup between", value1, value2, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andKeyLookupNotBetween(String value1, String value2) {
            addCriterion("key_lookup not between", value1, value2, "keyLookup");
            return (Criteria) this;
        }

        public Criteria andTargetTableIsNull() {
            addCriterion("target_table is null");
            return (Criteria) this;
        }

        public Criteria andTargetTableIsNotNull() {
            addCriterion("target_table is not null");
            return (Criteria) this;
        }

        public Criteria andTargetTableEqualTo(String value) {
            addCriterion("target_table =", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableNotEqualTo(String value) {
            addCriterion("target_table <>", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableGreaterThan(String value) {
            addCriterion("target_table >", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableGreaterThanOrEqualTo(String value) {
            addCriterion("target_table >=", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableLessThan(String value) {
            addCriterion("target_table <", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableLessThanOrEqualTo(String value) {
            addCriterion("target_table <=", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableLike(String value) {
            addCriterion("target_table like", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableNotLike(String value) {
            addCriterion("target_table not like", value, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableIn(List<String> values) {
            addCriterion("target_table in", values, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableNotIn(List<String> values) {
            addCriterion("target_table not in", values, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableBetween(String value1, String value2) {
            addCriterion("target_table between", value1, value2, "targetTable");
            return (Criteria) this;
        }

        public Criteria andTargetTableNotBetween(String value1, String value2) {
            addCriterion("target_table not between", value1, value2, "targetTable");
            return (Criteria) this;
        }

        public Criteria andIsUpdateIsNull() {
            addCriterion("is_update is null");
            return (Criteria) this;
        }

        public Criteria andIsUpdateIsNotNull() {
            addCriterion("is_update is not null");
            return (Criteria) this;
        }

        public Criteria andIsUpdateEqualTo(Byte value) {
            addCriterion("is_update =", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateNotEqualTo(Byte value) {
            addCriterion("is_update <>", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateGreaterThan(Byte value) {
            addCriterion("is_update >", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_update >=", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateLessThan(Byte value) {
            addCriterion("is_update <", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateLessThanOrEqualTo(Byte value) {
            addCriterion("is_update <=", value, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateIn(List<Byte> values) {
            addCriterion("is_update in", values, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateNotIn(List<Byte> values) {
            addCriterion("is_update not in", values, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateBetween(Byte value1, Byte value2) {
            addCriterion("is_update between", value1, value2, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andIsUpdateNotBetween(Byte value1, Byte value2) {
            addCriterion("is_update not between", value1, value2, "isUpdate");
            return (Criteria) this;
        }

        public Criteria andKeyStreamIsNull() {
            addCriterion("key_stream is null");
            return (Criteria) this;
        }

        public Criteria andKeyStreamIsNotNull() {
            addCriterion("key_stream is not null");
            return (Criteria) this;
        }

        public Criteria andKeyStreamEqualTo(String value) {
            addCriterion("key_stream =", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamNotEqualTo(String value) {
            addCriterion("key_stream <>", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamGreaterThan(String value) {
            addCriterion("key_stream >", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamGreaterThanOrEqualTo(String value) {
            addCriterion("key_stream >=", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamLessThan(String value) {
            addCriterion("key_stream <", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamLessThanOrEqualTo(String value) {
            addCriterion("key_stream <=", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamLike(String value) {
            addCriterion("key_stream like", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamNotLike(String value) {
            addCriterion("key_stream not like", value, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamIn(List<String> values) {
            addCriterion("key_stream in", values, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamNotIn(List<String> values) {
            addCriterion("key_stream not in", values, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamBetween(String value1, String value2) {
            addCriterion("key_stream between", value1, value2, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStreamNotBetween(String value1, String value2) {
            addCriterion("key_stream not between", value1, value2, "keyStream");
            return (Criteria) this;
        }

        public Criteria andKeyStream2IsNull() {
            addCriterion("key_stream2 is null");
            return (Criteria) this;
        }

        public Criteria andKeyStream2IsNotNull() {
            addCriterion("key_stream2 is not null");
            return (Criteria) this;
        }

        public Criteria andKeyStream2EqualTo(String value) {
            addCriterion("key_stream2 =", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2NotEqualTo(String value) {
            addCriterion("key_stream2 <>", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2GreaterThan(String value) {
            addCriterion("key_stream2 >", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2GreaterThanOrEqualTo(String value) {
            addCriterion("key_stream2 >=", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2LessThan(String value) {
            addCriterion("key_stream2 <", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2LessThanOrEqualTo(String value) {
            addCriterion("key_stream2 <=", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2Like(String value) {
            addCriterion("key_stream2 like", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2NotLike(String value) {
            addCriterion("key_stream2 not like", value, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2In(List<String> values) {
            addCriterion("key_stream2 in", values, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2NotIn(List<String> values) {
            addCriterion("key_stream2 not in", values, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2Between(String value1, String value2) {
            addCriterion("key_stream2 between", value1, value2, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyStream2NotBetween(String value1, String value2) {
            addCriterion("key_stream2 not between", value1, value2, "keyStream2");
            return (Criteria) this;
        }

        public Criteria andKeyConditionIsNull() {
            addCriterion("key_condition is null");
            return (Criteria) this;
        }

        public Criteria andKeyConditionIsNotNull() {
            addCriterion("key_condition is not null");
            return (Criteria) this;
        }

        public Criteria andKeyConditionEqualTo(String value) {
            addCriterion("key_condition =", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionNotEqualTo(String value) {
            addCriterion("key_condition <>", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionGreaterThan(String value) {
            addCriterion("key_condition >", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionGreaterThanOrEqualTo(String value) {
            addCriterion("key_condition >=", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionLessThan(String value) {
            addCriterion("key_condition <", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionLessThanOrEqualTo(String value) {
            addCriterion("key_condition <=", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionLike(String value) {
            addCriterion("key_condition like", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionNotLike(String value) {
            addCriterion("key_condition not like", value, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionIn(List<String> values) {
            addCriterion("key_condition in", values, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionNotIn(List<String> values) {
            addCriterion("key_condition not in", values, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionBetween(String value1, String value2) {
            addCriterion("key_condition between", value1, value2, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andKeyConditionNotBetween(String value1, String value2) {
            addCriterion("key_condition not between", value1, value2, "keyCondition");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamIsNull() {
            addCriterion("update_stream is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamIsNotNull() {
            addCriterion("update_stream is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamEqualTo(String value) {
            addCriterion("update_stream =", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamNotEqualTo(String value) {
            addCriterion("update_stream <>", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamGreaterThan(String value) {
            addCriterion("update_stream >", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamGreaterThanOrEqualTo(String value) {
            addCriterion("update_stream >=", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamLessThan(String value) {
            addCriterion("update_stream <", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamLessThanOrEqualTo(String value) {
            addCriterion("update_stream <=", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamLike(String value) {
            addCriterion("update_stream like", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamNotLike(String value) {
            addCriterion("update_stream not like", value, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamIn(List<String> values) {
            addCriterion("update_stream in", values, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamNotIn(List<String> values) {
            addCriterion("update_stream not in", values, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamBetween(String value1, String value2) {
            addCriterion("update_stream between", value1, value2, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateStreamNotBetween(String value1, String value2) {
            addCriterion("update_stream not between", value1, value2, "updateStream");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotIsNull() {
            addCriterion("update_or_not is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotIsNotNull() {
            addCriterion("update_or_not is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotEqualTo(String value) {
            addCriterion("update_or_not =", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotNotEqualTo(String value) {
            addCriterion("update_or_not <>", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotGreaterThan(String value) {
            addCriterion("update_or_not >", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotGreaterThanOrEqualTo(String value) {
            addCriterion("update_or_not >=", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotLessThan(String value) {
            addCriterion("update_or_not <", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotLessThanOrEqualTo(String value) {
            addCriterion("update_or_not <=", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotLike(String value) {
            addCriterion("update_or_not like", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotNotLike(String value) {
            addCriterion("update_or_not not like", value, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotIn(List<String> values) {
            addCriterion("update_or_not in", values, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotNotIn(List<String> values) {
            addCriterion("update_or_not not in", values, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotBetween(String value1, String value2) {
            addCriterion("update_or_not between", value1, value2, "updateOrNot");
            return (Criteria) this;
        }

        public Criteria andUpdateOrNotNotBetween(String value1, String value2) {
            addCriterion("update_or_not not between", value1, value2, "updateOrNot");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}