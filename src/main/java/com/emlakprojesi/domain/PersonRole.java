package com.emlakprojesi.domain;

public enum PersonRole {
    BUYER("Alan"),
    SELLER("Satan"),
    TENANT("Kiraci"),
    LANDLORD("Kiraya Veren");

    private final String label;

    PersonRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
