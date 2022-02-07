package endava.school.task.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class HeroDTO {
    @NotBlank
    @Size(min = 2, max = 50, message = "First name should be between 2 and 50 symbols.")
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 50, message = "Last name should be between 2 and 50 symbols.")
    private String lastName;
    @NotBlank
    @Size(min = 2, max = 50, message = "Hero name should be between 2 and 50 symbols.")
    private String heroName;
    @Email
    private String email;
    @PastOrPresent(message = "Birthday should be past or present")
    private LocalDate birthday;

    public HeroDTO() {
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
}
