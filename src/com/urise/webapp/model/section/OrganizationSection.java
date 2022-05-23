package com.urise.webapp.model.section;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private final List<Organization> organizationsList;

    public OrganizationSection(List<Organization> organizationList) {
        this.organizationsList = organizationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizationsList, that.organizationsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationsList);
    }

    @Override
    public String toString() {
        return organizationsList.toString();
    }
}
