package programmingmagic.domain.technology;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.program.Program;
import programmingmagic.domain.programminglanguage.ProgrammingLanguage;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Technology extends BaseEntity {

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


