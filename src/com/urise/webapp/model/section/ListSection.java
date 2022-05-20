package com.urise.webapp.model.section;

import java.util.ArrayList;

public class ListSection extends AbstractSection {
    ArrayList<String> descriptionList;

    public ListSection(ArrayList<String> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public ArrayList<String> getDescriptionList() {
        return descriptionList;
    }

    @Override
    public String toString() {
        return descriptionList.toString();
    }
}
