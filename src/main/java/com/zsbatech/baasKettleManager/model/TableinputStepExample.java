package com.zsbatech.baasKettleManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableinputStepExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TableinputStepExample() {
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

        public Criteria andTransStepNameIsNull() {
            addCriterion("trans_step_name is null");
            return (Criteria) this;
        }

        public Criteria andTransStepNameIsNotNull() {
            addCriterion("trans_step_name is not null");
            return (Criteria) this;
        }

        public Criteria andTransStepNameEqualTo(String value) {
            addCriterion("trans_step_name =", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameNotEqualTo(String value) {
            addCriterion("trans_step_name <>", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameGreaterThan(String value) {
            addCriterion("trans_step_name >", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameGreaterThanOrEqualTo(String value) {
            addCriterion("trans_step_name >=", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameLessThan(String value) {
            addCriterion("trans_step_name <", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameLessThanOrEqualTo(String value) {
            addCriterion("trans_step_name <=", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameLike(String value) {
            addCriterion("trans_step_name like", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameNotLike(String value) {
            addCriterion("trans_step_name not like", value, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameIn(List<String> values) {
            addCriterion("trans_step_name in", values, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameNotIn(List<String> values) {
            addCriterion("trans_step_name not in", values, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameBetween(String value1, String value2) {
            addCriterion("trans_step_name between", value1, value2, "transStepName");
            return (Criteria) this;
        }

        public Criteria andTransStepNameNotBetween(String value1, String value2) {
            addCriterion("trans_step_name not between", value1, value2, "transStepName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameIsNull() {
            addCriterion("db_connection_name is null");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameIsNotNull() {
            addCriterion("db_connection_name is not null");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameEqualTo(String value) {
            addCriterion("db_connection_name =", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameNotEqualTo(String value) {
            addCriterion("db_connection_name <>", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameGreaterThan(String value) {
            addCriterion("db_connection_name >", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("db_connection_name >=", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameLessThan(String value) {
            addCriterion("db_connection_name <", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameLessThanOrEqualTo(String value) {
            addCriterion("db_connection_name <=", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameLike(String value) {
            addCriterion("db_connection_name like", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameNotLike(String value) {
            addCriterion("db_connection_name not like", value, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameIn(List<String> values) {
            addCriterion("db_connection_name in", values, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameNotIn(List<String> values) {
            addCriterion("db_connection_name not in", values, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameBetween(String value1, String value2) {
            addCriterion("db_connection_name between", value1, value2, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andDbConnectionNameNotBetween(String value1, String value2) {
            addCriterion("db_connection_name not between", value1, value2, "dbConnectionName");
            return (Criteria) this;
        }

        public Criteria andExcSqlIsNull() {
            addCriterion("exc_sql is null");
            return (Criteria) this;
        }

        public Criteria andExcSqlIsNotNull() {
            addCriterion("exc_sql is not null");
            return (Criteria) this;
        }

        public Criteria andExcSqlEqualTo(String value) {
            addCriterion("exc_sql =", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlNotEqualTo(String value) {
            addCriterion("exc_sql <>", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlGreaterThan(String value) {
            addCriterion("exc_sql >", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlGreaterThanOrEqualTo(String value) {
            addCriterion("exc_sql >=", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlLessThan(String value) {
            addCriterion("exc_sql <", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlLessThanOrEqualTo(String value) {
            addCriterion("exc_sql <=", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlLike(String value) {
            addCriterion("exc_sql like", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlNotLike(String value) {
            addCriterion("exc_sql not like", value, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlIn(List<String> values) {
            addCriterion("exc_sql in", values, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlNotIn(List<String> values) {
            addCriterion("exc_sql not in", values, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlBetween(String value1, String value2) {
            addCriterion("exc_sql between", value1, value2, "excSql");
            return (Criteria) this;
        }

        public Criteria andExcSqlNotBetween(String value1, String value2) {
            addCriterion("exc_sql not between", value1, value2, "excSql");
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
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