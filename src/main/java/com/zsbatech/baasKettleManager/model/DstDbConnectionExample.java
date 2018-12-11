package com.zsbatech.baasKettleManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DstDbConnectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DstDbConnectionExample() {
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

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Integer value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Integer value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Integer value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Integer value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Integer value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Integer> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Integer> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Integer value1, Integer value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Integer value1, Integer value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andStepIdIsNull() {
            addCriterion("step_id is null");
            return (Criteria) this;
        }

        public Criteria andStepIdIsNotNull() {
            addCriterion("step_id is not null");
            return (Criteria) this;
        }

        public Criteria andStepIdEqualTo(Integer value) {
            addCriterion("step_id =", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdNotEqualTo(Integer value) {
            addCriterion("step_id <>", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdGreaterThan(Integer value) {
            addCriterion("step_id >", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("step_id >=", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdLessThan(Integer value) {
            addCriterion("step_id <", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdLessThanOrEqualTo(Integer value) {
            addCriterion("step_id <=", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdIn(List<Integer> values) {
            addCriterion("step_id in", values, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdNotIn(List<Integer> values) {
            addCriterion("step_id not in", values, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdBetween(Integer value1, Integer value2) {
            addCriterion("step_id between", value1, value2, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("step_id not between", value1, value2, "stepId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNull() {
            addCriterion("link_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNotNull() {
            addCriterion("link_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkIdEqualTo(Integer value) {
            addCriterion("link_id =", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotEqualTo(Integer value) {
            addCriterion("link_id <>", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThan(Integer value) {
            addCriterion("link_id >", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_id >=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThan(Integer value) {
            addCriterion("link_id <", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_id <=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIn(List<Integer> values) {
            addCriterion("link_id in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotIn(List<Integer> values) {
            addCriterion("link_id not in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("link_id between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_id not between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andDstTableIsNull() {
            addCriterion("dst_table is null");
            return (Criteria) this;
        }

        public Criteria andDstTableIsNotNull() {
            addCriterion("dst_table is not null");
            return (Criteria) this;
        }

        public Criteria andDstTableEqualTo(String value) {
            addCriterion("dst_table =", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableNotEqualTo(String value) {
            addCriterion("dst_table <>", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableGreaterThan(String value) {
            addCriterion("dst_table >", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableGreaterThanOrEqualTo(String value) {
            addCriterion("dst_table >=", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableLessThan(String value) {
            addCriterion("dst_table <", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableLessThanOrEqualTo(String value) {
            addCriterion("dst_table <=", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableLike(String value) {
            addCriterion("dst_table like", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableNotLike(String value) {
            addCriterion("dst_table not like", value, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableIn(List<String> values) {
            addCriterion("dst_table in", values, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableNotIn(List<String> values) {
            addCriterion("dst_table not in", values, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableBetween(String value1, String value2) {
            addCriterion("dst_table between", value1, value2, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableNotBetween(String value1, String value2) {
            addCriterion("dst_table not between", value1, value2, "dstTable");
            return (Criteria) this;
        }

        public Criteria andDstTableChIsNull() {
            addCriterion("dst_table_ch is null");
            return (Criteria) this;
        }

        public Criteria andDstTableChIsNotNull() {
            addCriterion("dst_table_ch is not null");
            return (Criteria) this;
        }

        public Criteria andDstTableChEqualTo(String value) {
            addCriterion("dst_table_ch =", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChNotEqualTo(String value) {
            addCriterion("dst_table_ch <>", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChGreaterThan(String value) {
            addCriterion("dst_table_ch >", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChGreaterThanOrEqualTo(String value) {
            addCriterion("dst_table_ch >=", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChLessThan(String value) {
            addCriterion("dst_table_ch <", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChLessThanOrEqualTo(String value) {
            addCriterion("dst_table_ch <=", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChLike(String value) {
            addCriterion("dst_table_ch like", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChNotLike(String value) {
            addCriterion("dst_table_ch not like", value, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChIn(List<String> values) {
            addCriterion("dst_table_ch in", values, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChNotIn(List<String> values) {
            addCriterion("dst_table_ch not in", values, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChBetween(String value1, String value2) {
            addCriterion("dst_table_ch between", value1, value2, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andDstTableChNotBetween(String value1, String value2) {
            addCriterion("dst_table_ch not between", value1, value2, "dstTableCh");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andDstColumnIsNull() {
            addCriterion("dst_column is null");
            return (Criteria) this;
        }

        public Criteria andDstColumnIsNotNull() {
            addCriterion("dst_column is not null");
            return (Criteria) this;
        }

        public Criteria andDstColumnEqualTo(String value) {
            addCriterion("dst_column =", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnNotEqualTo(String value) {
            addCriterion("dst_column <>", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnGreaterThan(String value) {
            addCriterion("dst_column >", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnGreaterThanOrEqualTo(String value) {
            addCriterion("dst_column >=", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnLessThan(String value) {
            addCriterion("dst_column <", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnLessThanOrEqualTo(String value) {
            addCriterion("dst_column <=", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnLike(String value) {
            addCriterion("dst_column like", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnNotLike(String value) {
            addCriterion("dst_column not like", value, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnIn(List<String> values) {
            addCriterion("dst_column in", values, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnNotIn(List<String> values) {
            addCriterion("dst_column not in", values, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnBetween(String value1, String value2) {
            addCriterion("dst_column between", value1, value2, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstColumnNotBetween(String value1, String value2) {
            addCriterion("dst_column not between", value1, value2, "dstColumn");
            return (Criteria) this;
        }

        public Criteria andDstSqlIsNull() {
            addCriterion("dst_sql is null");
            return (Criteria) this;
        }

        public Criteria andDstSqlIsNotNull() {
            addCriterion("dst_sql is not null");
            return (Criteria) this;
        }

        public Criteria andDstSqlEqualTo(String value) {
            addCriterion("dst_sql =", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlNotEqualTo(String value) {
            addCriterion("dst_sql <>", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlGreaterThan(String value) {
            addCriterion("dst_sql >", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlGreaterThanOrEqualTo(String value) {
            addCriterion("dst_sql >=", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlLessThan(String value) {
            addCriterion("dst_sql <", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlLessThanOrEqualTo(String value) {
            addCriterion("dst_sql <=", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlLike(String value) {
            addCriterion("dst_sql like", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlNotLike(String value) {
            addCriterion("dst_sql not like", value, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlIn(List<String> values) {
            addCriterion("dst_sql in", values, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlNotIn(List<String> values) {
            addCriterion("dst_sql not in", values, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlBetween(String value1, String value2) {
            addCriterion("dst_sql between", value1, value2, "dstSql");
            return (Criteria) this;
        }

        public Criteria andDstSqlNotBetween(String value1, String value2) {
            addCriterion("dst_sql not between", value1, value2, "dstSql");
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