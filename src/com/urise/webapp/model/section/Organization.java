package com.urise.webapp.model.section;

import com.urise.webapp.model.Link;
import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;

    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

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
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateFrom;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateTo;
        private String title;
        private String position;

        public Position() {
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String position) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, position);
        }

        public Position(LocalDate dateFrom, LocalDate dateTo, String title, String position) {
            Objects.requireNonNull(dateFrom, "startDate must not be null");
            Objects.requireNonNull(dateTo, "endDate must not be null");
//            Objects.requireNonNull(title, "title must not be null");
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
            this.position = position;
        }

        public LocalDate getDateFrom() {
            return dateFrom;
        }

        public LocalDate getDateTo() {
            return dateTo;
        }

        public String getDescription() {
            return position;
        }

        public String getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "\ndateFrom: " + dateFrom +
                    "\ndateTo: " + (dateTo == null ? "Текущее время" : dateTo) +
                    "\nДолжность: " + position +
                    "\nОписание деятельности: " + position +
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
            return position.equals(position1.position);
        }

        @Override
        public int hashCode() {
            int result = dateFrom.hashCode();
            result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
            result = 31 * result + (position != null ? position.hashCode() : 0);
            result = 31 * result + position.hashCode();
            return result;
        }
    }
}

