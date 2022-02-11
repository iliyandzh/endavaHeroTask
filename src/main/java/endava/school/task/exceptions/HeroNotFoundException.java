package endava.school.task.exceptions;

public class HeroNotFoundException extends RuntimeException{

    public HeroNotFoundException(Long id){
        super(String.format("Hero with id %d does not exist", id));
    }
}
