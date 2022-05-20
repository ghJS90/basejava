package com.urise.webapp.model.section;

import java.time.LocalDate;

public class Organization {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String organizationName;
    private final String position;
    private final String description;

    public Organization(LocalDate dateFrom, LocalDate dateTo, String organizationName, String position, String description) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.organizationName = organizationName;
        this.position = position;
        this.description = description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getDescription() {
        return description;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return dateFrom +
                "," + dateTo +
                "," + organizationName +
                "," + position +
                "," + description;
    }
}
