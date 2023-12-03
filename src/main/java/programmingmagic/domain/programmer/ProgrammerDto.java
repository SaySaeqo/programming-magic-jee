package programmingmagic.domain.programmer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import programmingmagic.domain.programmer.model.ProgrammerLevel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProgrammerDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class ProgrammerModel {
        private UUID uuid;
        private String name;
        private String title;
        private LocalDate birthday;
        private ProgrammerLevel level;

        ProgrammerModel(Programmer other) {
            this.uuid = other.getUuid();
            this.name = other.getName();
            this.title = other.getTitle();
            this.birthday = other.getBirthday();
            this.level = other.getLevel();
        }
    }

    public static class ProgrammersModel {
        private List<ProgrammerModel> programmers = new ArrayList<>();

        ProgrammersModel(List<Programmer> programmers) {
            programmers.forEach(programmer -> this.programmers.add(new ProgrammerModel(programmer)));
        }

        public List<ProgrammerModel> getProgrammers() {
            return programmers;
        }
    }

    public static ProgrammerModel getStandardInfo(Programmer programmer) {
        return new ProgrammerModel(programmer);
    }

    public static ProgrammersModel getStandardInfo(List<Programmer> programmers) {
        return new ProgrammersModel(programmers);
    }
}


