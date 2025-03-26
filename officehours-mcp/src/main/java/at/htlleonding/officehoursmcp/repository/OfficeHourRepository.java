package at.htlleonding.officehoursmcp.repository;

import at.htlleonding.officehoursmcp.entity.OfficeHour;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OfficeHourRepository implements PanacheRepository<OfficeHour> {
}
