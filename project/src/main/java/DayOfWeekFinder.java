import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DayOfWeekFinder {
    public String findNextDayOfWeek(String startDate, String dayOfWeek) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DayOfWeek day = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
            LocalDate date = LocalDate.parse(startDate, formatter);
            LocalDate nextDay;
            int difference =  day.ordinal() - date.getDayOfWeek().ordinal();
            if ( difference > 0 ) {
                nextDay = date.plusDays(difference);
            } else {
                nextDay = date.plusDays(difference + 7);
            }
            return nextDay.format(formatter);
        } catch (Exception e){
            return "Error";
        }
    }
}