package programmingmagic.domain.program;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.programmer.Programmer;
import programmingmagic.domain.technology.Technology;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Program extends BaseEntity {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private String description;
    private String code;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Technology getUsedTechnology() {
        return usedTechnology;
    }

    public void setUsedTechnology(Technology usedTechnology) {
        this.usedTechnology = usedTechnology;
    }

    public Programmer getAuthor() {
        return author;
    }

    public void setAuthor(Programmer author) {
        this.author = author;
    }
}


