package com.zsbatech.baasKettleManager.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobHopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JobHopExample() {
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

        public Criteria andFromStepIdIsNull() {
            addCriterion("from_step_id is null");
            return (Criteria) this;
        }

        public Criteria andFromStepIdIsNotNull() {
            addCriterion("from_step_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromStepIdEqualTo(Integer value) {
            addCriterion("from_step_id =", value, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdNotEqualTo(Integer value) {
            addCriterion("from_step_id <>", value, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdGreaterThan(Integer value) {
            addCriterion("from_step_id >", value, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_step_id >=", value, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdLessThan(Integer value) {
            addCriterion("from_step_id <", value, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdLessThanOrEqualTo(Integer value) {
            addCriterion("from_step_id <=", value, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdIn(List<Integer> values) {
            addCriterion("from_step_id in", values, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdNotIn(List<Integer> values) {
            addCriterion("from_step_id not in", values, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdBetween(Integer value1, Integer value2) {
            addCriterion("from_step_id between", value1, value2, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andFromStepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("from_step_id not between", value1, value2, "fromStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdIsNull() {
            addCriterion("to_step_id is null");
            return (Criteria) this;
        }

        public Criteria andToStepIdIsNotNull() {
            addCriterion("to_step_id is not null");
            return (Criteria) this;
        }

        public Criteria andToStepIdEqualTo(Integer value) {
            addCriterion("to_step_id =", value, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdNotEqualTo(Integer value) {
            addCriterion("to_step_id <>", value, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdGreaterThan(Integer value) {
            addCriterion("to_step_id >", value, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_step_id >=", value, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdLessThan(Integer value) {
            addCriterion("to_step_id <", value, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_step_id <=", value, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdIn(List<Integer> values) {
            addCriterion("to_step_id in", values, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdNotIn(List<Integer> values) {
            addCriterion("to_step_id not in", values, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdBetween(Integer value1, Integer value2) {
            addCriterion("to_step_id between", value1, value2, "toStepId");
            return (Criteria) this;
        }

        public Criteria andToStepIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_step_id not between", value1, value2, "toStepId");
            return (Criteria) this;
        }

        public Criteria andHopSequenceIsNull() {
            addCriterion("hop_sequence is null");
            return (Criteria) this;
        }

        public Criteria andHopSequenceIsNotNull() {
            addCriterion("hop_sequence is not null");
            return (Criteria) this;
        }

        public Criteria andHopSequenceEqualTo(Byte value) {
            addCriterion("hop_sequence =", value, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceNotEqualTo(Byte value) {
            addCriterion("hop_sequence <>", value, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceGreaterThan(Byte value) {
            addCriterion("hop_sequence >", value, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceGreaterThanOrEqualTo(Byte value) {
            addCriterion("hop_sequence >=", value, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceLessThan(Byte value) {
            addCriterion("hop_sequence <", value, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceLessThanOrEqualTo(Byte value) {
            addCriterion("hop_sequence <=", value, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceIn(List<Byte> values) {
            addCriterion("hop_sequence in", values, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceNotIn(List<Byte> values) {
            addCriterion("hop_sequence not in", values, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceBetween(Byte value1, Byte value2) {
            addCriterion("hop_sequence between", value1, value2, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andHopSequenceNotBetween(Byte value1, Byte value2) {
            addCriterion("hop_sequence not between", value1, value2, "hopSequence");
            return (Criteria) this;
        }

        public Criteria andConditionIsNull() {
            addCriterion("condition is null");
            return (Criteria) this;
        }

        public Criteria andConditionIsNotNull() {
            addCriterion("condition is not null");
            return (Criteria) this;
        }

        public Criteria andConditionEqualTo(Byte value) {
            addCriterion("condition =", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotEqualTo(Byte value) {
            addCriterion("condition <>", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionGreaterThan(Byte value) {
            addCriterion("condition >", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionGreaterThanOrEqualTo(Byte value) {
            addCriterion("condition >=", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionLessThan(Byte value) {
            addCriterion("condition <", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionLessThanOrEqualTo(Byte value) {
            addCriterion("condition <=", value, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionIn(List<Byte> values) {
            addCriterion("condition in", values, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotIn(List<Byte> values) {
            addCriterion("condition not in", values, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionBetween(Byte value1, Byte value2) {
            addCriterion("condition between", value1, value2, "condition");
            return (Criteria) this;
        }

        public Criteria andConditionNotBetween(Byte value1, Byte value2) {
            addCriterion("condition not between", value1, value2, "condition");
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

        public Criteria andJobMetaIdIsNull() {
            addCriterion("job_meta_id is null");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdIsNotNull() {
            addCriterion("job_meta_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdEqualTo(Integer value) {
            addCriterion("job_meta_id =", value, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdNotEqualTo(Integer value) {
            addCriterion("job_meta_id <>", value, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdGreaterThan(Integer value) {
            addCriterion("job_meta_id >", value, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_meta_id >=", value, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdLessThan(Integer value) {
            addCriterion("job_meta_id <", value, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdLessThanOrEqualTo(Integer value) {
            addCriterion("job_meta_id <=", value, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdIn(List<Integer> values) {
            addCriterion("job_meta_id in", values, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdNotIn(List<Integer> values) {
            addCriterion("job_meta_id not in", values, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdBetween(Integer value1, Integer value2) {
            addCriterion("job_meta_id between", value1, value2, "jobMetaId");
            return (Criteria) this;
        }

        public Criteria andJobMetaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("job_meta_id not between", value1, value2, "jobMetaId");
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