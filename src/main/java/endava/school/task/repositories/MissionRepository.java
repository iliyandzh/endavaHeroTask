package endava.school.task.repositories;

import endava.school.task.models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MissionRepository extends JpaRepository<Mission, Long> {
}
