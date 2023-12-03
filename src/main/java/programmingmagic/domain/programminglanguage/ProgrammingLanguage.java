package programmingmagic.domain.programminglanguage;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.programminglanguage.model.ProgrammingLanguageType;
import programmingmagic.domain.technology.Technology;

import java.util.List;
import java.util.UUID;

@Entity
public class ProgrammingLanguage extends BaseEntity {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private ProgrammingLanguageType type;
    private Boolean isCompiled;
    private Boolean isStrongTyped;
    @OneToMany(mappedBy = "language")
    private List<Technology> technologies;

    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage(String name, ProgrammingLanguageType type, Boolean isCompiled, Boolean isStrongTyped) {
        this.name = name;
        this.type = type;
        this.isCompiled = isCompiled;
        this.isStrongTyped = isStrongTyped;
    }

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

    public ProgrammingLanguageType getType() {
        return type;
    }

    public void setType(ProgrammingLanguageType type) {
        this.type = type;
    }

    public Boolean getCompiled() {
        return isCompiled;
    }

    public void setCompiled(Boolean compiled) {
        isCompiled = compiled;
    }

    public Boolean getStrongTyped() {
        return isStrongTyped;
    }

    public void setStrongTyped(Boolean strongTyped) {
        isStrongTyped = strongTyped;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}


