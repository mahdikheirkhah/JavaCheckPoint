import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeFinder {
    public int calculateAge(String date) {
       try {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          LocalDate birthTime = LocalDate.parse(date, formatter);
          Period Age = Period.between(birthTime, LocalDate.now());
          return Age.getYears()>-1 ? Age.getYears(): -1 ;
       } catch (Exception e){
           return -1;
       }
    }
}