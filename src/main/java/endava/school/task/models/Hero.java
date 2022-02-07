package endava.school.task.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heroes")
public class Hero {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotEmpty
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotEmpty
    @Column(name = "hero_name", nullable = false)
    private String heroName;
    @NotEmpty
    @Column(name = "email", nullable = false)
    private String email;
    @NotEmpty
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "heroes_and_missions", joinColumns = @JoinColumn(name = "hero_id"), inverseJoinColumns = @JoinColumn(name = "mission_id"))
    private List<Mission> missions;

    public Hero() {
        this.missions = new ArrayList<>();
    }

    public Hero(Long id, String firstName, String lastName, String heroName, String email, LocalDate birthday, List<Mission> missions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.heroName = heroName;
        this.email = email;
        this.birthday = birthday;
        this.missions = missions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "Hero{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", heroName='" + heroName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
