package endava.school.task.services;


import endava.school.task.models.Hero;
import endava.school.task.models.Mission;

import java.util.Optional;

public interface MissionService {

    Optional<Mission> findById(Long id);

    void deleteMission(Long id);

    Mission save(Mission mission);

}
