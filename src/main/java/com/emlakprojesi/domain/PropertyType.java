package com.emlakprojesi.domain;

public enum PropertyType {
    APARTMENT("Daire"),
    HOUSE("Mustakil Ev"),
    LAND("Arsa"),
    OFFICE("Ofis"),
    SHOP("Dukkan");

    private final String label;

    PropertyType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
