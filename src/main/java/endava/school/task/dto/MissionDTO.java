package endava.school.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class MissionDTO {
    @NotBlank(message = "Mission name should not be empty")
    private String missionName;

    @JsonProperty
    private boolean completed;

    private List<HeroDTO> heroDTOS;

    public MissionDTO() {

    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<HeroDTO> getHero() {
        return heroDTOS;
    }

    public void setHero(List<HeroDTO> heroDTOS) {
        this.heroDTOS = heroDTOS;
    }
}
