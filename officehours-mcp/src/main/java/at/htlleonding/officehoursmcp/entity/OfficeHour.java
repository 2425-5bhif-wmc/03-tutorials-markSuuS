package at.htlleonding.officehoursmcp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;

@Entity
@Table(name = "OHMCP_OFFICE_HOUR")
public class OfficeHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OH_ID")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OH_TEACHER")
    @NotNull
    private Teacher teacher;

    @Column(name = "OH_DAY")
    private DayOfWeek day;

    @Column(name = "OH_UNIT")
    private Integer unit;

    @Column(name = "OH_ROOM")
    private String room;

    @Column(name = "OH_BY_APPOINTMENT")
    private boolean byAppointment;

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isByAppointment() {
        return byAppointment;
    }

    public void setByAppointment(boolean byAppointment) {
        this.byAppointment = byAppointment;
    }

    @Override
    public String toString() {
        return "OfficeHour{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", day=" + day +
                ", unit=" + unit +
                ", room='" + room + '\'' +
                ", byAppointment=" + byAppointment +
                '}';
    }
}
