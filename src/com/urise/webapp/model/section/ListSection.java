package com.urise.webapp.model.section;

import java.io.Serializable;
import java.util.*;

public class ListSection extends AbstractSection implements Serializable {
    private final List<String> descriptionList = new ArrayList<>();

    public void addStrings(String... text){
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
