package com.financial.api.com.financial.api.repository.projection;

import com.financial.api.com.financial.api.model.LaunchType;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by netof on 25/08/2018.
 */
public class SummaryLaunch {

    private Long code;
    private String description;
    private LocalDate dueDate;
    private LocalDate payDate;
    private BigDecimal value;
    private LaunchType type;
    private String category;
    private String people;

    public SummaryLaunch(Long code, String description, LocalDate dueDate, LocalDate payDate, BigDecimal value,
                         LaunchType type, String category, String people) {
        this.code = code;
        this.description = description;
        this.dueDate = dueDate;
        this.payDate = payDate;
        this.value = value;
        this.type = type;
        this.category = category;
        this.people = people;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LaunchType getType() {
        return type;
    }

    public void setType(LaunchType type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
