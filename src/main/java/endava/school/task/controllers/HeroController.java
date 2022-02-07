package endava.school.task.controllers;

import endava.school.task.dto.HeroDTO;
import endava.school.task.models.Hero;
import endava.school.task.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class HeroController {
    private HeroService heroService;
    private ModelMapper modelMapper;

    @Autowired
    public HeroController(HeroService heroService, ModelMapper modelMapper){
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<HeroDTO> findById(@PathVariable(value = "id")Long id){
        Optional<Hero> heroToMap = heroService.findById(id);
        if(heroToMap.isEmpty()){
            throw new IllegalArgumentException("Hero with id: "+ id + "not found");
        }else{
            HeroDTO hero = modelMapper.map(heroToMap.get(), HeroDTO.class);
            return ResponseEntity.ok(hero);
        }
    }

}
