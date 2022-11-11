package utils;

import java.time.LocalDate;

public class DateTimeUtils {

    public static LocalDate getDateByStringDDMon(String date) {
        int dd = Integer.parseInt(date.substring(0, date.indexOf(' ')));
        String month =date.substring(date.lastIndexOf(" ") + 1);
        int mon = getMonthByValue(month);
        return LocalDate.of(LocalDate.now().getYear(), mon, dd);
    }

    private static int getMonthByValue(String month) {
        switch(month) {
            case "января" : return 1;
            case "февраля" : return 2;
            case "марта" : return 3;
            case "апреля" : return 4;
            case "мая" : return 5;
            case "июня" : return 6;
            case "июля" : return 7;
            case "августа" : return 8;
            case "сентября" : return 9;
            case "октября" : return 10;
            case "ноября" : return 11;
            case "декабря" : return 12;
            default: return 0;
        }
    }
}
