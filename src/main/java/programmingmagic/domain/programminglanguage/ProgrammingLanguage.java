package programmingmagic.domain.programminglanguage;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.programminglanguage.model.ProgrammingLanguageType;
import programmingmagic.domain.technology.Technology;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class ProgrammingLanguage extends BaseEntity {
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
}


