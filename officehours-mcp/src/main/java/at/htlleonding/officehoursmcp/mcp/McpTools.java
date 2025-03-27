package at.htlleonding.officehoursmcp.mcp;

import at.htlleonding.officehoursmcp.entity.OfficeHour;
import at.htlleonding.officehoursmcp.entity.Teacher;
import at.htlleonding.officehoursmcp.repository.OfficeHourRepository;
import at.htlleonding.officehoursmcp.repository.TeacherRepository;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/teachers")
public class McpTools {
    @Inject
    TeacherRepository teacherRepository;

    @Inject
    OfficeHourRepository officeHourRepository;

    @Tool(description = "Alle Lehrerinnen und Lehrer anzeigen")
    @Transactional
    @GET
    @Path("/all")
    public String getAllTeachers(){
        return teacherRepository.getAllTeachersAsString();
    }

    @Tool(description = "Sprechstunde einer Lehrerin oder eines Lehrers anzeigen")
    @Transactional
    @GET
    @Path("/details-per-teacher")
    public String getDetailsPerTeacher(
            @ToolArg(description = "Vor- oder Nachname des Lehrers") String name
    ){
        name = "stütz";
        return officeHourRepository.getAllOfficeHoursByTeacherNameAsString(name);
    }
}
