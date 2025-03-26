package at.htlleonding.officehoursmcp.parser;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class DayOfWeekParser {
    private Map<String, DayOfWeek> dayOfWeekMap = new HashMap<>();

    public DayOfWeek parse(String dayOfWeek) {
        dayOfWeek = dayOfWeek.trim().toUpperCase();
        dayOfWeekMap.put("MONTAG", DayOfWeek.MONDAY);
        dayOfWeekMap.put("DIENSTAG", DayOfWeek.TUESDAY);
        dayOfWeekMap.put("MITTWOCH", DayOfWeek.WEDNESDAY);
        dayOfWeekMap.put("DONNERSTAG", DayOfWeek.THURSDAY);
        dayOfWeekMap.put("FREITAG", DayOfWeek.FRIDAY);

        if(dayOfWeekMap.containsKey(dayOfWeek)) {
            return dayOfWeekMap.get(dayOfWeek);
        } else {
            throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        }
    }
}
