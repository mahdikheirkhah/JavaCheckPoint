import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MonthlyPeriod {
    public String calculatePeriod(String startDate, String endDate) {
        if (startDate == null || endDate == null || startDate.isBlank() || endDate.isBlank()) {
            return "Error";
        }
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate1 = LocalDate.parse(startDate, formatter);
            LocalDate endDate1 = LocalDate.parse(endDate, formatter);
            if (startDate1.isAfter(endDate1)) {
                LocalDate temp = startDate1;
                startDate1 = endDate1;
                endDate1 = temp;
            }
            Period period = Period.between(startDate1, endDate1);
            int years = period.getYears();
            int months = period.getMonths();
            String output="";
            if(years == 1){
                output += String.valueOf(years) + " year";
            }else if(years > 1){
                output += String.valueOf(years) + " years";
            }
            if(months >= 1) {
                if (years >= 1 ){
                    output += " and ";
                }
                output += String.valueOf(months);
                if (months > 1 ){
                    output += " months";
                } else {
                    output += " month";
                }
            }
            return output;
        } catch (Exception e) {
            return "Error";
        }
    }
}