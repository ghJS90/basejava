package com.urise.webapp.model.section;

public class StringSection extends AbstractSection {
    String description;

    public StringSection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
