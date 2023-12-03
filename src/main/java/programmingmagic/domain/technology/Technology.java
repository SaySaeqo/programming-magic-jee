package programmingmagic.domain.technology;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.program.Program;
import programmingmagic.domain.programminglanguage.ProgrammingLanguage;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Technology extends BaseEntity {
    @Override
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

    public Boolean getFramework() {
        return isFramework;
    }

    public void setFramework(Boolean framework) {
        isFramework = framework;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public void setLanguage(ProgrammingLanguage language) {
        this.language = language;
    }

    public List<Program> getApplications() {
        return applications;
    }

    public void setApplications(List<Program> applications) {
        this.applications = applications;
    }

    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private Boolean isFramework;
    private LocalDate dateOfCreation;
    @ManyToOne
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage language;
    @OneToMany(mappedBy = "usedTechnology")
    private List<Program> applications;

    public Technology() {}

    public Technology(String name, Boolean isFramework, LocalDate dateOfCreation) {
        this.name = name;
        this.isFramework = isFramework;
        this.dateOfCreation = dateOfCreation;
    }
}


