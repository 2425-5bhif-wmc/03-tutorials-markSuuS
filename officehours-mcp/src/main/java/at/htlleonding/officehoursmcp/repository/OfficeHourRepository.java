package at.htlleonding.officehoursmcp.repository;

import at.htlleonding.officehoursmcp.entity.OfficeHour;
import at.htlleonding.officehoursmcp.entity.Teacher;
import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OfficeHourRepository implements PanacheRepository<OfficeHour> {
    @Inject
    JPAStreamer jpaStreamer;

    public String getAllOfficeHoursByTeacherNameAsString(String name) {
        String finalName = name.toLowerCase().trim();
        return jpaStreamer.stream(OfficeHour.class)
                .filter(oh -> oh.getTeacher().getFullName().toLowerCase().contains(finalName))
                .map(OfficeHour::toString)
                .collect(Collectors.joining(", "));
    }
}
