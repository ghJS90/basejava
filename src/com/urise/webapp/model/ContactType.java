package com.urise.webapp.model;

public enum ContactType {
    PHONE("Номер телефона"),
    SKYPE("Skype"),
    EMAIL("e-mail"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
