package com.gdsig.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class BdOrgunitMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BdOrgunitMappingExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andOrgunitIdIsNull() {
            addCriterion("orgunit_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdIsNotNull() {
            addCriterion("orgunit_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdEqualTo(Integer value) {
            addCriterion("orgunit_id =", value, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdNotEqualTo(Integer value) {
            addCriterion("orgunit_id <>", value, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdGreaterThan(Integer value) {
            addCriterion("orgunit_id >", value, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orgunit_id >=", value, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdLessThan(Integer value) {
            addCriterion("orgunit_id <", value, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdLessThanOrEqualTo(Integer value) {
            addCriterion("orgunit_id <=", value, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdIn(List<Integer> values) {
            addCriterion("orgunit_id in", values, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdNotIn(List<Integer> values) {
            addCriterion("orgunit_id not in", values, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdBetween(Integer value1, Integer value2) {
            addCriterion("orgunit_id between", value1, value2, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orgunit_id not between", value1, value2, "orgunitId");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameIsNull() {
            addCriterion("orgunit_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameIsNotNull() {
            addCriterion("orgunit_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameEqualTo(String value) {
            addCriterion("orgunit_name =", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameNotEqualTo(String value) {
            addCriterion("orgunit_name <>", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameGreaterThan(String value) {
            addCriterion("orgunit_name >", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameGreaterThanOrEqualTo(String value) {
            addCriterion("orgunit_name >=", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameLessThan(String value) {
            addCriterion("orgunit_name <", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameLessThanOrEqualTo(String value) {
            addCriterion("orgunit_name <=", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameLike(String value) {
            addCriterion("orgunit_name like", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameNotLike(String value) {
            addCriterion("orgunit_name not like", value, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameIn(List<String> values) {
            addCriterion("orgunit_name in", values, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameNotIn(List<String> values) {
            addCriterion("orgunit_name not in", values, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameBetween(String value1, String value2) {
            addCriterion("orgunit_name between", value1, value2, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andOrgunitNameNotBetween(String value1, String value2) {
            addCriterion("orgunit_name not between", value1, value2, "orgunitName");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNull() {
            addCriterion("parent_name is null");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNotNull() {
            addCriterion("parent_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentNameEqualTo(String value) {
            addCriterion("parent_name =", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotEqualTo(String value) {
            addCriterion("parent_name <>", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThan(String value) {
            addCriterion("parent_name >", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_name >=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThan(String value) {
            addCriterion("parent_name <", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThanOrEqualTo(String value) {
            addCriterion("parent_name <=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLike(String value) {
            addCriterion("parent_name like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotLike(String value) {
            addCriterion("parent_name not like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameIn(List<String> values) {
            addCriterion("parent_name in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotIn(List<String> values) {
            addCriterion("parent_name not in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameBetween(String value1, String value2) {
            addCriterion("parent_name between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotBetween(String value1, String value2) {
            addCriterion("parent_name not between", value1, value2, "parentName");
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