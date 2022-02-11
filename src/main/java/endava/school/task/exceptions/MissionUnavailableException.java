package endava.school.task.exceptions;

public class MissionUnavailableException extends RuntimeException{
    public MissionUnavailableException(Long id){
        super("Mission with id is Unavailable : " + id);
    }
}
