package at.htlleonding.officehoursmcp.repository;

import at.htlleonding.officehoursmcp.entity.OfficeHour;
import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.stream.Collectors;

@ApplicationScoped
public class OfficeHourRepository implements PanacheRepository<OfficeHour> {
    @Inject
    JPAStreamer jpaStreamer;

    public String getAllOfficeHoursByTeacherNameAsString(String name) {
        String finalName = name.toLowerCase().trim();
        String officeHours = jpaStreamer.stream(OfficeHour.class)
                .filter(oh -> oh.getTeacher() != null && oh.getTeacher().getFullName().toLowerCase().contains(finalName))
                .map(OfficeHour::toString)
                .collect(Collectors.joining(", "));

        return officeHours.isEmpty()
                ? "Keine Sprechstundendaten zu Lehrerin oder Lehrer %s gefunden!".formatted(finalName)
                : officeHours;
    }

    public String getTeachersByRoom(String room) {
        String finalRoom = room.toLowerCase().trim().replace("_", "");
        String teachers = jpaStreamer.stream(OfficeHour.class)
                .filter(oh -> oh.getRoom() != null && oh.getRoom().toLowerCase().replace("_", "").contains(finalRoom))
                .map(oh -> oh.getTeacher().toString())
                .collect(Collectors.joining(", "));

        return teachers.isEmpty()
                ? "Keine Lehrerinnen und Lehrer in Raum %s gefunden!".formatted(finalRoom)
                : teachers;
    }
}
