package at.htlleonding.officehoursmcp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "OHMCP_TEACHER")
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private Long id;

    @Column(name = "T_FIRST_NAME")
    private String firstName;

    @Column(name = "T_LAST_NAME")
    private String lastName;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
