package com.company;

public class Kiss {
    private final String from;

    public Kiss(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Kiss{" +
                "from='" + from + '\'' +
                '}';
    }
}
