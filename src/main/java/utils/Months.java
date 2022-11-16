package utils;

import java.util.Arrays;

public enum Months {
    January("января", 1),
    FEBRUARY("февраля", 2),
    MARCH("марта", 3),
    APRIL("апреля", 4),
    MAY("мая", 5),
    JUNE("июня", 6),
    JULY("июля", 7),
    AUGUST("августа", 8),
    SEPTEMBER("сентября", 9),
    OCTOBER("октября", 10),
    NOVEMBER("ноября", 11),
    DECEMBER("декабря", 12);

    final String name;
    final int number;

    Months(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static int getMonth(String name) {
        return Arrays.stream(values())
                .filter(month -> month.name.equals(name))
                .findFirst().get().number;
    }
}
