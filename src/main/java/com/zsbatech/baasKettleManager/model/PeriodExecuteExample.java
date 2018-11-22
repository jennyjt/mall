package com.zsbatech.baasKettleManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodExecuteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PeriodExecuteExample() {
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

        public Criteria andDataMigrationIdIsNull() {
            addCriterion("data_migration_id is null");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdIsNotNull() {
            addCriterion("data_migration_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdEqualTo(Integer value) {
            addCriterion("data_migration_id =", value, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdNotEqualTo(Integer value) {
            addCriterion("data_migration_id <>", value, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdGreaterThan(Integer value) {
            addCriterion("data_migration_id >", value, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_migration_id >=", value, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdLessThan(Integer value) {
            addCriterion("data_migration_id <", value, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_migration_id <=", value, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdIn(List<Integer> values) {
            addCriterion("data_migration_id in", values, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdNotIn(List<Integer> values) {
            addCriterion("data_migration_id not in", values, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdBetween(Integer value1, Integer value2) {
            addCriterion("data_migration_id between", value1, value2, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andDataMigrationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_migration_id not between", value1, value2, "dataMigrationId");
            return (Criteria) this;
        }

        public Criteria andIsExistIsNull() {
            addCriterion("is_exist is null");
            return (Criteria) this;
        }

        public Criteria andIsExistIsNotNull() {
            addCriterion("is_exist is not null");
            return (Criteria) this;
        }

        public Criteria andIsExistEqualTo(Byte value) {
            addCriterion("is_exist =", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotEqualTo(Byte value) {
            addCriterion("is_exist <>", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistGreaterThan(Byte value) {
            addCriterion("is_exist >", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_exist >=", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistLessThan(Byte value) {
            addCriterion("is_exist <", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistLessThanOrEqualTo(Byte value) {
            addCriterion("is_exist <=", value, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistIn(List<Byte> values) {
            addCriterion("is_exist in", values, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotIn(List<Byte> values) {
            addCriterion("is_exist not in", values, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistBetween(Byte value1, Byte value2) {
            addCriterion("is_exist between", value1, value2, "isExist");
            return (Criteria) this;
        }

        public Criteria andIsExistNotBetween(Byte value1, Byte value2) {
            addCriterion("is_exist not between", value1, value2, "isExist");
            return (Criteria) this;
        }

        public Criteria andSqlStringIsNull() {
            addCriterion("sql_string is null");
            return (Criteria) this;
        }

        public Criteria andSqlStringIsNotNull() {
            addCriterion("sql_string is not null");
            return (Criteria) this;
        }

        public Criteria andSqlStringEqualTo(String value) {
            addCriterion("sql_string =", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringNotEqualTo(String value) {
            addCriterion("sql_string <>", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringGreaterThan(String value) {
            addCriterion("sql_string >", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringGreaterThanOrEqualTo(String value) {
            addCriterion("sql_string >=", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringLessThan(String value) {
            addCriterion("sql_string <", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringLessThanOrEqualTo(String value) {
            addCriterion("sql_string <=", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringLike(String value) {
            addCriterion("sql_string like", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringNotLike(String value) {
            addCriterion("sql_string not like", value, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringIn(List<String> values) {
            addCriterion("sql_string in", values, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringNotIn(List<String> values) {
            addCriterion("sql_string not in", values, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringBetween(String value1, String value2) {
            addCriterion("sql_string between", value1, value2, "sqlString");
            return (Criteria) this;
        }

        public Criteria andSqlStringNotBetween(String value1, String value2) {
            addCriterion("sql_string not between", value1, value2, "sqlString");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(Byte value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(Byte value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(Byte value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(Byte value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(Byte value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<Byte> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<Byte> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(Byte value1, Byte value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
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