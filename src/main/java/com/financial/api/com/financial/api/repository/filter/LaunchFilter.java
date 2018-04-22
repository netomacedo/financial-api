package com.financial.api.com.financial.api.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by netof on 13/03/2018.
 */
public class LaunchFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDataFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateTo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDateFrom() {
        return dueDataFrom;
    }

    public void setDueDateFrom(LocalDate duaDateFrom) {
        this.dueDataFrom = duaDateFrom;
    }

    public LocalDate getDueDateTo() {
        return dueDateTo;
    }

    public void setDueDateTo(LocalDate duaDateTo) {
        this.dueDateTo = duaDateTo;
    }
}
