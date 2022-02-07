package endava.school.task.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name = "mission_name", nullable = false)
    private String missionName;
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;
    @ManyToMany(mappedBy = "missions")
    private List<Hero> superHeroTeam;

    public Mission() {
        this.superHeroTeam = new ArrayList<>();
    }

    public Mission(Long id, String missionName, Boolean isCompleted) {
        this.superHeroTeam = new ArrayList<>();
        this.id = id;
        this.missionName = missionName;
        this.isCompleted = isCompleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public List<Hero> getSuperHeroTeam() {
        return superHeroTeam;
    }

    public void setSuperHeroTeam(List<Hero> superHeroTeam) {
        this.superHeroTeam = superHeroTeam;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionName='" + missionName + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
