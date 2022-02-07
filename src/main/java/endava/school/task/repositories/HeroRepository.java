package endava.school.task.repositories;

import endava.school.task.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeroRepository extends JpaRepository<Hero, Long> {
}
