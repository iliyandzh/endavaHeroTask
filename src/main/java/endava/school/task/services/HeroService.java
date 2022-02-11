package endava.school.task.services;

import endava.school.task.models.Hero;

import java.util.Optional;

public interface HeroService {
    Optional<Hero> findById(Long id);
    void deleteHero(Long id);
    Hero save(Hero hero);
}
