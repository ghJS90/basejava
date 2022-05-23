package com.urise.webapp.model.section;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return dateFrom.equals(that.dateFrom) && Objects.equals(dateTo, that.dateTo) && organizationName.equals(that.organizationName) && Objects.equals(position, that.position) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, organizationName, position, description);
    }
}
