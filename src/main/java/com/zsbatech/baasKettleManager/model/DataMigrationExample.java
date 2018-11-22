package com.zsbatech.baasKettleManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataMigrationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataMigrationExample() {
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

        public Criteria andInputStepIdIsNull() {
            addCriterion("input_step_id is null");
            return (Criteria) this;
        }

        public Criteria andInputStepIdIsNotNull() {
            addCriterion("input_step_id is not null");
            return (Criteria) this;
        }

        public Criteria andInputStepIdEqualTo(Integer value) {
            addCriterion("input_step_id =", value, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdNotEqualTo(Integer value) {
            addCriterion("input_step_id <>", value, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdGreaterThan(Integer value) {
            addCriterion("input_step_id >", value, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("input_step_id >=", value, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdLessThan(Integer value) {
            addCriterion("input_step_id <", value, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdLessThanOrEqualTo(Integer value) {
            addCriterion("input_step_id <=", value, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdIn(List<Integer> values) {
            addCriterion("input_step_id in", values, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdNotIn(List<Integer> values) {
            addCriterion("input_step_id not in", values, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdBetween(Integer value1, Integer value2) {
            addCriterion("input_step_id between", value1, value2, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andInputStepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("input_step_id not between", value1, value2, "inputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdIsNull() {
            addCriterion("output_step_id is null");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdIsNotNull() {
            addCriterion("output_step_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdEqualTo(Integer value) {
            addCriterion("output_step_id =", value, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdNotEqualTo(Integer value) {
            addCriterion("output_step_id <>", value, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdGreaterThan(Integer value) {
            addCriterion("output_step_id >", value, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("output_step_id >=", value, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdLessThan(Integer value) {
            addCriterion("output_step_id <", value, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdLessThanOrEqualTo(Integer value) {
            addCriterion("output_step_id <=", value, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdIn(List<Integer> values) {
            addCriterion("output_step_id in", values, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdNotIn(List<Integer> values) {
            addCriterion("output_step_id not in", values, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdBetween(Integer value1, Integer value2) {
            addCriterion("output_step_id between", value1, value2, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andOutputStepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("output_step_id not between", value1, value2, "outputStepId");
            return (Criteria) this;
        }

        public Criteria andHopIdIsNull() {
            addCriterion("hop_id is null");
            return (Criteria) this;
        }

        public Criteria andHopIdIsNotNull() {
            addCriterion("hop_id is not null");
            return (Criteria) this;
        }

        public Criteria andHopIdEqualTo(Integer value) {
            addCriterion("hop_id =", value, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdNotEqualTo(Integer value) {
            addCriterion("hop_id <>", value, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdGreaterThan(Integer value) {
            addCriterion("hop_id >", value, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hop_id >=", value, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdLessThan(Integer value) {
            addCriterion("hop_id <", value, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdLessThanOrEqualTo(Integer value) {
            addCriterion("hop_id <=", value, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdIn(List<Integer> values) {
            addCriterion("hop_id in", values, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdNotIn(List<Integer> values) {
            addCriterion("hop_id not in", values, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdBetween(Integer value1, Integer value2) {
            addCriterion("hop_id between", value1, value2, "hopId");
            return (Criteria) this;
        }

        public Criteria andHopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hop_id not between", value1, value2, "hopId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdIsNull() {
            addCriterion("src_dbconn_id is null");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdIsNotNull() {
            addCriterion("src_dbconn_id is not null");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdEqualTo(Integer value) {
            addCriterion("src_dbconn_id =", value, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdNotEqualTo(Integer value) {
            addCriterion("src_dbconn_id <>", value, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdGreaterThan(Integer value) {
            addCriterion("src_dbconn_id >", value, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("src_dbconn_id >=", value, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdLessThan(Integer value) {
            addCriterion("src_dbconn_id <", value, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdLessThanOrEqualTo(Integer value) {
            addCriterion("src_dbconn_id <=", value, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdIn(List<Integer> values) {
            addCriterion("src_dbconn_id in", values, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdNotIn(List<Integer> values) {
            addCriterion("src_dbconn_id not in", values, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdBetween(Integer value1, Integer value2) {
            addCriterion("src_dbconn_id between", value1, value2, "srcDbconnId");
            return (Criteria) this;
        }

        public Criteria andSrcDbconnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("src_dbconn_id not between", value1, value2, "srcDbconnId");
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

        public Criteria andDstDbconnIdIsNull() {
            addCriterion("dst_dbconn_id is null");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdIsNotNull() {
            addCriterion("dst_dbconn_id is not null");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdEqualTo(Integer value) {
            addCriterion("dst_dbconn_id =", value, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdNotEqualTo(Integer value) {
            addCriterion("dst_dbconn_id <>", value, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdGreaterThan(Integer value) {
            addCriterion("dst_dbconn_id >", value, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dst_dbconn_id >=", value, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdLessThan(Integer value) {
            addCriterion("dst_dbconn_id <", value, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdLessThanOrEqualTo(Integer value) {
            addCriterion("dst_dbconn_id <=", value, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdIn(List<Integer> values) {
            addCriterion("dst_dbconn_id in", values, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdNotIn(List<Integer> values) {
            addCriterion("dst_dbconn_id not in", values, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdBetween(Integer value1, Integer value2) {
            addCriterion("dst_dbconn_id between", value1, value2, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andDstDbconnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dst_dbconn_id not between", value1, value2, "dstDbconnId");
            return (Criteria) this;
        }

        public Criteria andRecordsIsNull() {
            addCriterion("records is null");
            return (Criteria) this;
        }

        public Criteria andRecordsIsNotNull() {
            addCriterion("records is not null");
            return (Criteria) this;
        }

        public Criteria andRecordsEqualTo(Integer value) {
            addCriterion("records =", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsNotEqualTo(Integer value) {
            addCriterion("records <>", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsGreaterThan(Integer value) {
            addCriterion("records >", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsGreaterThanOrEqualTo(Integer value) {
            addCriterion("records >=", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsLessThan(Integer value) {
            addCriterion("records <", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsLessThanOrEqualTo(Integer value) {
            addCriterion("records <=", value, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsIn(List<Integer> values) {
            addCriterion("records in", values, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsNotIn(List<Integer> values) {
            addCriterion("records not in", values, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsBetween(Integer value1, Integer value2) {
            addCriterion("records between", value1, value2, "records");
            return (Criteria) this;
        }

        public Criteria andRecordsNotBetween(Integer value1, Integer value2) {
            addCriterion("records not between", value1, value2, "records");
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