package programmingmagic.domain.programmer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import programmingmagic.base.BaseEntity;
import programmingmagic.domain.program.Program;
import programmingmagic.domain.programmer.model.ProgrammerLevel;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Programmer extends BaseEntity {

    @Id
    private UUID uuid = UUID.randomUUID();
    private String name;
    private String title;
    private LocalDate birthday;
    private ProgrammerLevel level;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Program> applications;

    public Programmer(String name, LocalDate birthday, ProgrammerLevel level) {
        this.name = name;
        this.birthday = birthday;
        this.level = level;
    }
}


