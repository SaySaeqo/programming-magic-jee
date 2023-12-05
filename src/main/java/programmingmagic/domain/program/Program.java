package programmingmagic.domain.program;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.programmer.Programmer;
import programmingmagic.domain.technology.Technology;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Entity
public class Program extends BaseEntity {

    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String code;
    @Past(message = "Date of creation cannot be in the future")
    private LocalDate dateOfCreation;
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology usedTechnology;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Programmer author;

    public Program() {
    }

    public Program(String name, String description, String code, LocalDate dateOfCreation, Programmer author) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.dateOfCreation = dateOfCreation;
        this.author = author;
    }


    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setUsedTechnology(Technology usedTechnology) {
        this.usedTechnology = usedTechnology;
    }

    public void setAuthor(Programmer author) {
        this.author = author;
    }
}


