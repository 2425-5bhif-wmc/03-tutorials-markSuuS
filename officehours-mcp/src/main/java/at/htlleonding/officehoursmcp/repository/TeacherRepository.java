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
public class TeacherRepository implements PanacheRepository<Teacher> {
    @Inject
    JPAStreamer jpaStreamer;

    public String getAllTeachersAsString() {
        return jpaStreamer.stream(Teacher.class)
                .map(Teacher::getFullName)
                .collect(Collectors.joining(", "));
    }
}
