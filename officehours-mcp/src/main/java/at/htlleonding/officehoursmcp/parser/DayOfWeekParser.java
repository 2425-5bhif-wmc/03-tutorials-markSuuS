package at.htlleonding.officehoursmcp.parser;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.DayOfWeek;
import java.util.Map;

@ApplicationScoped
public class DayOfWeekParser {
    private final Map<String, DayOfWeek> DAY_OF_WEEK_MAP = Map.of(
            "MONTAG", DayOfWeek.MONDAY,
            "DIENSTAG", DayOfWeek.TUESDAY,
            "MITTWOCH", DayOfWeek.WEDNESDAY,
            "DONNERSTAG", DayOfWeek.THURSDAY,
            "FREITAG", DayOfWeek.FRIDAY
    );

    public DayOfWeek parse(String dayOfWeek) {
        dayOfWeek = dayOfWeek.toUpperCase().trim();

        if(DAY_OF_WEEK_MAP.containsKey(dayOfWeek)) {
            return DAY_OF_WEEK_MAP.get(dayOfWeek);
        } else {
            throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        }
    }
}
