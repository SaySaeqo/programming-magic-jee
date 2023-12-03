package programmingmagic.domain.program;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramDto {

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    @Setter
    public static class ProgramModel {
        private String name;
        private String description;
        private String code;
        private LocalDate dateOfCreation;

        ProgramModel(Program other) {
            this.name = other.getName();
            this.description = other.getDescription();
            this.code = other.getCode();
            this.dateOfCreation = other.getDateOfCreation();
        }

    }

    public static class ProgramsModel {
        private List<ProgramModel> programs = new ArrayList<>();

        ProgramsModel(List<Program> programs) {
            for (Program program : programs) {
                this.programs.add(new ProgramModel(program));
            }
        }

        public List<ProgramModel> getPrograms() {
            return programs;
        }
    }

    public static ProgramModel getStandardInfo(Program program)  {
        return new ProgramModel(program);
    }

    public static ProgramsModel getStandardInfo(List<Program> programs) {
        return new ProgramsModel(programs);
    }
}


