package endava.school.task.controllers;

import endava.school.task.dto.MissionDTO;
import endava.school.task.exceptions.HeroNotFoundException;
import endava.school.task.exceptions.MissionNotFoundException;
import endava.school.task.exceptions.MissionUnavailableException;
import endava.school.task.models.Hero;
import endava.school.task.models.Mission;
import endava.school.task.services.HeroService;
import endava.school.task.services.MissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

@Controller
public class MissionController {
    private HeroService heroService;
    private MissionService missionService;
    private ModelMapper modelMapper;

    @Autowired
    public MissionController(HeroService heroService, MissionService missionService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.missionService = missionService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    ResponseEntity<MissionDTO> findById(@PathVariable(value = "id") Long id) {
        Optional<Mission> missionToMap = missionService.findById(id);
        if (missionToMap.isEmpty()) {
            throw new MissionNotFoundException(id);
        } else {
            MissionDTO mission = modelMapper.map(missionToMap.get(), MissionDTO.class);
            return ResponseEntity.ok(mission);
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    ResponseEntity<MissionDTO> deleteMission(@PathVariable(value = "id") Long id) {
        Optional<Mission> optionalMissionEntity = missionService.findById(id);
        if (optionalMissionEntity.isEmpty()) {
            throw new MissionNotFoundException(id);
        }
        MissionDTO mapped = modelMapper.map(optionalMissionEntity.get(), MissionDTO.class);
        missionService.deleteMission(id);
        return ResponseEntity.ok(mapped);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ResponseEntity<MissionDTO> saveMission(@Valid @RequestBody MissionDTO missionDTO) {
        Mission mission = modelMapper.map(missionDTO, Mission.class);
        Mission savedMission = missionService.save(mission);
        MissionDTO mapped = modelMapper.map(savedMission, MissionDTO.class);
        return ResponseEntity.ok(mapped);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    ResponseEntity<MissionDTO> updateMission(@PathVariable(value = "id")Long id, @Valid @RequestBody MissionDTO missionDTO){
        Optional<Mission> optionalMissionEntity = missionService.findById(id);
        if(optionalMissionEntity.isEmpty()){
            throw new MissionNotFoundException(id);
        }
        Mission mission = modelMapper.map(missionDTO, Mission.class);
        mission.setId(id);
        Mission editedMission = missionService.save(mission);
        MissionDTO mapped = modelMapper.map(editedMission, MissionDTO.class);
        return ResponseEntity.ok(mapped);
    }

    @RequestMapping(value = "/assign/{missionId}/{heroId}", method = RequestMethod.POST)
    ResponseEntity<MissionDTO> assignMissionToHero(@PathVariable(value = "missionId")Long missionId, @PathVariable(value = "heroId")Long heroId){
        Optional<Mission> optionalMission = missionService.findById(missionId);
        if(optionalMission.isEmpty()){
            throw new MissionNotFoundException(missionId);
        }
        if(optionalMission.get().getCompleted()){
            throw new MissionUnavailableException(missionId);
        }
        Optional<Hero> optionalSuperHeroEntity = heroService.findById(heroId);
        if(optionalSuperHeroEntity.isEmpty()){
            throw new HeroNotFoundException(missionId);
        }
        List<Mission> missions = new ArrayList<>();
        missions.add(optionalMission.get());
        List<Hero> heroes = new ArrayList<>();
        heroes.add(optionalSuperHeroEntity.get());
        optionalMission.get().setSuperHeroTeam(heroes);
        optionalSuperHeroEntity.get().setMissions(missions);

        Mission mission = missionService.save(optionalMission.get());
        heroService.save(optionalSuperHeroEntity.get());
        MissionDTO mapped = modelMapper.map(mission, MissionDTO.class);

        return ResponseEntity.ok(mapped);
    }

}
