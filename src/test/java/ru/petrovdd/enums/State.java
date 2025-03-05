package ru.petrovdd.enums;

public enum State {

    NCR("NCR"),
    UTTAR_PRADESH("Uttar Pradesh"),
    HARYANA("Haryana"),
    RAJASTHAN("Rajasthan");

    private final String nameState;

    State(String nameState) {
        this.nameState = nameState;
    }

    public String getNameState() {
        return nameState;
    }
}
