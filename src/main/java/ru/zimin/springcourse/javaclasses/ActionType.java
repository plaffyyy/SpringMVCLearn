package ru.zimin.springcourse.javaclasses;

public class ActionType {

    private String value;

    public ActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public String getOperation() {
        return switch (value) {
            case ("multiplication") -> "*";
            case ("division") -> "/";
            case ("addition") -> "+";
            case ("subtraction") -> "-";

            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }


}
