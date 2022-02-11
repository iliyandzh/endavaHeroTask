package endava.school.task.controllers;

import endava.school.task.dto.HeroDTO;
import endava.school.task.exceptions.HeroNotFoundException;
import endava.school.task.models.Hero;
import endava.school.task.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    ResponseEntity<HeroDTO> findById(@PathVariable(value = "id")Long id){
        Optional<Hero> heroToMap = heroService.findById(id);
        if(heroToMap.isEmpty()){
            throw new IllegalArgumentException(String.format("Hero with id %d not found", id));
        }else{
            HeroDTO hero = modelMapper.map(heroToMap.get(), HeroDTO.class);
            return ResponseEntity.ok(hero);
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    ResponseEntity<HeroDTO> deleteHero(@PathVariable(value = "id")Long id){
        Optional<Hero> heroToDelete = heroService.findById(id);
        if(heroToDelete.isEmpty()){
            throw new HeroNotFoundException(id);
        }
        HeroDTO hero = modelMapper.map(heroToDelete.get(), HeroDTO.class);
        heroService.deleteHero(id);
        return ResponseEntity.ok(hero);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    ResponseEntity<HeroDTO> saveHero(@Valid @RequestBody HeroDTO heroDTO){
        Hero hero = modelMapper.map(heroDTO, Hero.class);
        Hero savedHero = heroService.save(hero);
        HeroDTO mapped = modelMapper.map(savedHero, HeroDTO.class);
        return ResponseEntity.ok(mapped);
    }

    @RequestMapping(value = "{/update/{id}}", method = RequestMethod.PUT)
    ResponseEntity<HeroDTO> updateHero(
            @PathVariable(value = "id")Long id,
            @Valid @RequestBody HeroDTO heroDTO){Optional<Hero> optionalSuperHeroEntity = heroService.findById(id);
        if(optionalSuperHeroEntity.isEmpty()){
            throw new HeroNotFoundException(id);
        }
        Hero hero = modelMapper.map(heroDTO, Hero.class);
        hero.setId(id);
        Hero editedHero = heroService.save(hero);
        HeroDTO mappedHero = modelMapper.map(editedHero, HeroDTO.class);
        return ResponseEntity.ok(mappedHero);
    }

}
