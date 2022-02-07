package endava.school.task.controllers;

import endava.school.task.dto.MissionDTO;
import endava.school.task.models.Mission;
import endava.school.task.services.HeroService;
import endava.school.task.services.MissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class MissionController {
    private HeroService heroService;
    private MissionService missionService;
    private ModelMapper modelMapper;

    @Autowired
    public MissionController(HeroService heroService, MissionService missionService,ModelMapper modelMapper){
        this.heroService = heroService;
        this.missionService = missionService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<MissionDTO> findById(@PathVariable(value = "id")Long id){
        Optional<Mission> missionToMap = missionService.findById(id);
        if(missionToMap.isEmpty()){
            throw new IllegalArgumentException("Mission with id: "+ id + "not found");
        }else{
            MissionDTO mission = modelMapper.map(missionToMap.get(), MissionDTO.class);
            return ResponseEntity.ok(mission);
        }
    }

}
