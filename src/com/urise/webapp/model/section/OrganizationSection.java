package com.urise.webapp.model.section;

import java.util.List;

public class OrganizationSection extends AbstractSection {
    private final List<Organization> organizationsList;

    public OrganizationSection(List<Organization> organizationList) {
        this.organizationsList = organizationList;
    }

    @Override
    public String toString() {
        return organizationsList.toString();
    }
}
