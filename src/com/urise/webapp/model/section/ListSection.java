package com.urise.webapp.model.section;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> descriptionList = new ArrayList<>();

    public ListSection() {
    }

    public ListSection(String... descriptionList) {
        this(Arrays.asList(descriptionList));
    }

    public ListSection(List<String> descriptionList) {
        Objects.requireNonNull(descriptionList, "items must not be null");
        this.descriptionList = descriptionList;
    }

    public void addStrings(String... text) {
        Objects.requireNonNull(text, "text must not be null");
        descriptionList.addAll(Arrays.asList(text));
    }

    public List<String> getDescriptionList() {
        return descriptionList;
    }

    @Override
    public String toString() {
        return descriptionList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return descriptionList.equals(that.descriptionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionList);
    }
}
