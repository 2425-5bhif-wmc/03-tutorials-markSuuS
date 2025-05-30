package at.htlleonding.officehoursmcp.repository;

import at.htlleonding.officehoursmcp.entity.Teacher;
import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.stream.Collectors;

@ApplicationScoped
public class TeacherRepository implements PanacheRepository<Teacher> {
    @Inject
    JPAStreamer jpaStreamer;

    public String getAllTeachersAsString() {
        String teachers = jpaStreamer.stream(Teacher.class)
                .map(Teacher::getFullName)
                .collect(Collectors.joining(", "));

        return teachers.isEmpty()
                ? "Keine Lehrer in der Datenbank gefunden."
                : teachers;
    }
}
