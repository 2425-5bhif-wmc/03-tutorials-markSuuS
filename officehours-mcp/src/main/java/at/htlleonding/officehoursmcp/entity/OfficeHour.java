package at.htlleonding.officehoursmcp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

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
    @NotNull
    private DayOfWeek day;

    @Column(name = "OH_START_UNIT")
    @PositiveOrZero
    private int startUnit;

    @Column(name = "OH_END_UNIT")
    @PositiveOrZero
    private int endUnit;

    @Column(name = "OH_ROOM")
    @NotNull
    private String room;
}
