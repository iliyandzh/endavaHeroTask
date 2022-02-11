package endava.school.task.services;

import endava.school.task.models.Hero;
import endava.school.task.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {
    @Autowired
    private HeroRepository heroRepo;

    public HeroServiceImpl() {
    }

    @Override
    public Optional<Hero> findById(Long id) {
        return heroRepo.findById(id);
    }

    @Override
    public void deleteHero(Long id) {
        heroRepo.deleteById(id);
    }

    @Override
    public Hero save(Hero hero) {
        return heroRepo.save(hero);
    }

}
