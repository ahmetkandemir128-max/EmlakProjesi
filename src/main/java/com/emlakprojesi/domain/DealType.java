package com.emlakprojesi.domain;

public enum DealType {
    SALE("Satis"),
    RENTAL("Kiralama");

    private final String label;

    DealType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
