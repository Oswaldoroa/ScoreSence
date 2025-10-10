package ScoreSense.App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CoachDTO {
    private Long id;
    @NotBlank(message = "DTO must not be null or empty")
    @Size(min = 2, max = 100, message = "DTO must be between 2 and 100 characters")
    private String name;
    private String nationality;
    private Integer experiencedYears;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    } 
    public Integer getExperiencedYears() {
        return experiencedYears;
    }
    public void setExperiencedYears(Integer experiencedYears) {
        this.experiencedYears = experiencedYears;
    }
}
