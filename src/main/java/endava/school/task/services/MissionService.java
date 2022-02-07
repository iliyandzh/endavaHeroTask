package endava.school.task.services;


import endava.school.task.models.Mission;

import java.util.Optional;

public interface MissionService {

    Optional<Mission> findById(Long id);
    Mission save(Mission hero);

}
