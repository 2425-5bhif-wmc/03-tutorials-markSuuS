package at.htlleonding.officehoursmcp.control;

import at.htlleonding.officehoursmcp.entity.OfficeHour;
import at.htlleonding.officehoursmcp.entity.Teacher;
import at.htlleonding.officehoursmcp.parser.DayOfWeekParser;
import at.htlleonding.officehoursmcp.repository.OfficeHourRepository;
import at.htlleonding.officehoursmcp.repository.TeacherRepository;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@ApplicationScoped
public class InsertBean {
    @Inject
    TeacherRepository teacherRepository;

    @Inject
    OfficeHourRepository officeHourRepository;

    @Inject
    DayOfWeekParser dayOfWeekParser;

    @ConfigProperty(name = "officehours-csv-path")
    String officeHoursCsvPath;

    @Transactional
    void readCsvAndInsert(@Observes StartupEvent event) {
        try(InputStream stream = getClass().getClassLoader().getResourceAsStream(officeHoursCsvPath)) {
            String[] content = new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\n");

            for(int i = 1; i < Arrays.stream(content).count(); i++) {
                String[] parts = content[i].split("\t");

                Teacher teacher = new Teacher();
                teacher.setFirstName(parts[0].split(" ")[0].trim());
                teacher.setLastName(parts[0].split(" ")[1].trim());
                teacherRepository.persist(teacher);

                OfficeHour officeHour = new OfficeHour();
                officeHour.setTeacher(teacher);

                if(parts[1].trim().toLowerCase().contains("vereinbarung")) {
                    officeHour.setByAppointment(true);
                } else {
                    officeHour.setByAppointment(false);
                    officeHour.setDay(dayOfWeekParser.parse(parts[1]));
                    officeHour.setUnit(Integer.parseInt(parts[3].split("\\.")[0]));

                    if(Arrays.stream(parts).count() >= 7){
                        officeHour.setRoom(parts[6]);
                    }
                }

                officeHourRepository.persist(officeHour);
            }

            Log.infof("%d teachers in database", teacherRepository.count());
            Log.infof("%d officeHours in database", officeHourRepository.count());
        } catch (Exception e) {
            Log.errorf("Error reading csv-file");
            throw new RuntimeException(e);
        }
    }
}
