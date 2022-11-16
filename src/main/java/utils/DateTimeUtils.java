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
        return Months.getMonth(month);
    }
}
