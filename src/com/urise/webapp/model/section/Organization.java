package com.urise.webapp.model.section;

import com.urise.webapp.model.Link;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private  static final long serialVersionUID = 1L;

    private final Link homePage;
    private final List<Position> positions = new ArrayList<>();

    public Organization(String organizationName, String url) {
        this.homePage = new Link(organizationName, url);
    }

    public void addPosition(Position pos) {
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

    public static class Position implements Serializable {
        private final LocalDate dateFrom;
        private final LocalDate dateTo;
        private final String position;
        private final String description;

        public Position(LocalDate dateFrom, LocalDate dateTo, String position, String description) {
            Objects.requireNonNull(dateFrom, "dateFrom must not be null");
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.position = position;
            this.description = description;
        }

        public LocalDate getDateFrom() {
            return dateFrom;
        }

        public LocalDate getDateTo() {
            return dateTo;
        }

        public String getDescription() {
            return description;
        }

        public String getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "\ndateFrom: " + dateFrom +
                    "\ndateTo: " + (dateTo == null ? "Текущее время" : dateTo) +
                    "\nДолжность: " + position +
                    "\nОписание деятельности: " + description +
                    "\n*\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position1 = (Position) o;

            if (!dateFrom.equals(position1.dateFrom)) return false;
            if (!Objects.equals(dateTo, position1.dateTo)) return false;
            if (!Objects.equals(position, position1.position)) return false;
            return description.equals(position1.description);
        }

        @Override
        public int hashCode() {
            int result = dateFrom.hashCode();
            result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
            result = 31 * result + (position != null ? position.hashCode() : 0);
            result = 31 * result + description.hashCode();
            return result;
        }
    }
}

