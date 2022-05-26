package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
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
        if (dateTo == null) {
            return "\nPosition:\n" +
                    "\ndateFrom:\n" + dateFrom +
                    "\ndateTo:\n" + "Текущее время" +
                    "\nДолжность:\n" + position +
                    "\nОписание деятельности:\n" + description +
                    "\n*\n";
        }
        return "\nPosition:\n" +
                "\ndateFrom:\n" + dateFrom +
                "\ndateTo:\n" + dateTo +
                "\nДолжность:\n" + position +
                "\nОписание деятельности:\n" + description +
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
