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

public class McpTools {
    @Inject
    TeacherRepository teacherRepository;

    @Inject
    OfficeHourRepository officeHourRepository;

    @Tool(description = "Alle Lehrerinnen und Lehrer der HTL Leonding anzeigen")
    @Transactional
    public String getAllTeachers(){
        if(teacherRepository.count() == 0) {
            return "Leider keine Lehrerinnen oder Lehrer gefunden";
        }

        return teacherRepository.getAllTeachersAsString();
    }

    @Tool(description = "Sprechstunde einer Lehrerin oder eines Lehrers anzeigen")
    @Transactional
    public String getDetailsPerTeacher(
            @ToolArg(description = "Vor- oder Nachname des Lehrers") String name
    ){
        return officeHourRepository.getAllOfficeHoursByTeacherNameAsString(name);
    }

    @Tool(description = "Alle Lehrerinnen und Lehrer eines Raumes anzeigen")
    @Transactional
    public String getAllTeachersByRoom(
            @ToolArg(description = "Raum") String room
    ){
        return officeHourRepository.getTeachersByRoom(room);
    }
}
