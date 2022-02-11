package endava.school.task.exceptions;

public class MissionNotFoundException extends RuntimeException{
    public MissionNotFoundException(Long id){
        super(String.format("Mission with id: %d not found", id));
    }
}
