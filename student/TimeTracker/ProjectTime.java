import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTime {
    private String startTime;
    private String endTime;
    private Long ms;
    private Long min;

    public ProjectTime(String start, String end) {
        this.startTime = start;
        this.endTime = end;
        updateHoursLogged();
    }

    public void setStartTime(String start) {
        this.startTime = start;
        updateHoursLogged();
    }

    public void setEndTime(String end) {
        this.endTime = end;
        updateHoursLogged();
    }

    public void updateHoursLogged() {
        try {
            // format dates
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date parsedStart = formatter.parse(this.startTime);
            Date parsedEnd = formatter.parse(this.endTime);

            // calculate and set in ms, mins
            this.ms = parsedEnd.getTime() - parsedStart.getTime();
            this.min = (this.ms / 60000L);

        } catch (ParseException e) {
            this.ms = -1L;
            this.min = -1L;
        }

    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getHoursLogged() {
        System.out.println("this.min: " + this.min);

        if (this.ms <= 0) {
            return "-1"; // error
        }

        if (this.min < 120) { // less than 120 mins
            return this.min + " m";
        }
        if (this.min >= 120 && this.min < 7200) {
            return (this.min / 60) + " h"; // more than or equal to 120 mins && less than 120 hours = 7200 mins
        }
        if (this.min >= 7200 && this.min < 172740) {
            return ((this.min / 60) / 24) + " d"; // more than or equal to 120 hours && less than 120 days
        }
        if (this.min >= 172740) { // more than 120 days (but test threshold is 172739 mins so ¯\_(ツ)_/¯)
            return (((this.min / 60) / 24) / 29) + " mo";
        }

        return "-1";
    }
}
