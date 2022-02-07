package endava.school.task.services;

import endava.school.task.models.Mission;
import endava.school.task.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MissionServiceImpl implements MissionService{
    @Autowired
    private MissionRepository missionRepo;

    public MissionServiceImpl() {
    }

    @Override
    public Optional<Mission> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Mission save(Mission hero) {
        return null;
    }
}

