package com.urise.webapp.model.section;

import com.urise.webapp.model.Link;
import com.urise.webapp.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private final Link homePage;
    private final List<Position> positions = new ArrayList<>();

    public Organization(String organizationName, String url) {
        this.homePage = new Link(organizationName, url);
    }

    public void addPosition(Position pos){
        positions.add(pos);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "\nOrganization{\n" +
                "\nhomePage:\n" + homePage +
                "\n positions:\n" + positions +
                '}';
    }
}

