package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramService;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ViewScoped
@Named
public class ProgramViewController implements Serializable {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    ProgramService programService;
    ProgrammerService programmerService;

    UUID programmerUuid;
    UUID programUuid;
    Program program = new Program(); //?? czemu to jest potrzebne?
    Programmer programmer;

    public ProgramViewController() {}

    @EJB
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @EJB
    public void setProgrammerService(ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    public List<Program> getPrograms() {
        programmerService.getBaseService().getRepository().get_entityManager().getEntityManagerFactory().getCache().evictAll();
        programmerService.getBaseService().getRepository().get_entityManager().clear();
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

