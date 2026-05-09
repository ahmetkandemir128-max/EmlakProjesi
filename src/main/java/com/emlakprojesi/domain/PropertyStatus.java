package com.emlakprojesi.domain;

public enum PropertyStatus {
    FOR_SALE("Satilik"),
    FOR_RENT("Kiralik"),
    SOLD("Satildi"),
    RENTED("Kiraya Verildi");

    private final String label;

    PropertyStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
