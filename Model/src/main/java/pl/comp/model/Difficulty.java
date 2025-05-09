package pl.comp.model;

public enum Difficulty {

    Easy(41), Medium(32), Hard(21), Ultra(16);

    private final int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}