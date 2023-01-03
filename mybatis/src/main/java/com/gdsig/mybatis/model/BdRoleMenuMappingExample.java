package com.gdsig.mybatis.model;

import java.util.ArrayList;
import java.util.List;

public class BdRoleMenuMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BdRoleMenuMappingExample() {
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

        public Criteria andRoleMenuIdIsNull() {
            addCriterion("role_menu_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdIsNotNull() {
            addCriterion("role_menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdEqualTo(Integer value) {
            addCriterion("role_menu_id =", value, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdNotEqualTo(Integer value) {
            addCriterion("role_menu_id <>", value, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdGreaterThan(Integer value) {
            addCriterion("role_menu_id >", value, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_menu_id >=", value, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdLessThan(Integer value) {
            addCriterion("role_menu_id <", value, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_menu_id <=", value, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdIn(List<Integer> values) {
            addCriterion("role_menu_id in", values, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdNotIn(List<Integer> values) {
            addCriterion("role_menu_id not in", values, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("role_menu_id between", value1, value2, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andRoleMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_menu_id not between", value1, value2, "roleMenuId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdIsNull() {
            addCriterion("menu_mapping_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdIsNotNull() {
            addCriterion("menu_mapping_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdEqualTo(Integer value) {
            addCriterion("menu_mapping_id =", value, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdNotEqualTo(Integer value) {
            addCriterion("menu_mapping_id <>", value, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdGreaterThan(Integer value) {
            addCriterion("menu_mapping_id >", value, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_mapping_id >=", value, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdLessThan(Integer value) {
            addCriterion("menu_mapping_id <", value, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdLessThanOrEqualTo(Integer value) {
            addCriterion("menu_mapping_id <=", value, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdIn(List<Integer> values) {
            addCriterion("menu_mapping_id in", values, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdNotIn(List<Integer> values) {
            addCriterion("menu_mapping_id not in", values, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdBetween(Integer value1, Integer value2) {
            addCriterion("menu_mapping_id between", value1, value2, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andMenuMappingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_mapping_id not between", value1, value2, "menuMappingId");
            return (Criteria) this;
        }

        public Criteria andCheckedIsNull() {
            addCriterion("checked is null");
            return (Criteria) this;
        }

        public Criteria andCheckedIsNotNull() {
            addCriterion("checked is not null");
            return (Criteria) this;
        }

        public Criteria andCheckedEqualTo(Boolean value) {
            addCriterion("checked =", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotEqualTo(Boolean value) {
            addCriterion("checked <>", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedGreaterThan(Boolean value) {
            addCriterion("checked >", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("checked >=", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedLessThan(Boolean value) {
            addCriterion("checked <", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedLessThanOrEqualTo(Boolean value) {
            addCriterion("checked <=", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedIn(List<Boolean> values) {
            addCriterion("checked in", values, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotIn(List<Boolean> values) {
            addCriterion("checked not in", values, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedBetween(Boolean value1, Boolean value2) {
            addCriterion("checked between", value1, value2, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("checked not between", value1, value2, "checked");
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