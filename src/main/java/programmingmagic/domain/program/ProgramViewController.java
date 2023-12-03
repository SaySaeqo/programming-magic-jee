package programmingmagic.domain.program;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programmingmagic.domain.programmer.Programmer;
import programmingmagic.domain.programmer.ProgrammerService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ProgramViewController implements Serializable {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @EJB
    ProgramService programService;
    @EJB
    ProgrammerService programmerService;

    UUID programmerUuid;
    UUID programUuid;
    Program program = new Program(); //?? czemu to jest potrzebne?
    Programmer programmer;

    public ProgramViewController() {}

//    @EJB
//    public void setProgramService(ProgramService programService) {
//        this.programService = programService;
//    }
//
//    @EJB
//    public void setProgrammerService(ProgrammerService programmerService) {
//        this.programmerService = programmerService;
//    }

    public List<Program> getPrograms() {
        programmerService.getBaseService().getRepository().getEntityManager().getEntityManagerFactory().getCache().evictAll();
        programmerService.getBaseService().getRepository().getEntityManager().clear();
        return programmerUuid != null ? programmerService.getBaseService().get(programmerUuid).getApplications() : programService.getAll();
    }

    public void initProgram(){
        program = programUuid != null ? programService.get(programUuid) : new Program();
    }

    public void initProgrammer(){
        if (programmerUuid == null) return;
        programmer = programmerService.getBaseService().get(programmerUuid);
    }

    public String delete(Program program) {
        programService.delete(program);
        return "programs?faces-redirect=true";
    }

    public String save() {
        program.setAuthor(programmerService.getBaseService().get(programmerUuid));
        programService.save(program);
        return "programs?faces-redirect=true";
    }

    public String cancel() {
        return "programs?faces-redirect=true";
    }

    public UUID getProgrammerUuid() {
        return programmerUuid;
    }

    public UUID getProgramUuid() {
        return programUuid;
    }

    public Program getProgram() {
        return program;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammerUuid(UUID programmerUuid) {
        this.programmerUuid = programmerUuid;
    }

    public void setProgramUuid(UUID programUuid) {
        this.programUuid = programUuid;
    }
}

