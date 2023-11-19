package pl.edu.pg.eti.kask.s180171.programmingmagic.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramService;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Named
public class ProgramViewController implements Serializable {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    ProgramService service;
    ProgrammerService programmerService;

    UUID programmerUuid;
    UUID programUuid;
    Program program = new Program(); //?? czemu to jest potrzebne?
    Programmer programmer;

    @Inject
    public ProgramViewController(ProgramService service, ProgrammerService programmerService) {
        this.service = service;
        this.programmerService = programmerService;
    }

    public List<Program> getPrograms() {
        programmerService.getBaseService().getRepository().get_entityManager().getEntityManagerFactory().getCache().evictAll();
        programmerService.getBaseService().getRepository().get_entityManager().clear();
        return programmerUuid != null ? programmerService.get(programmerUuid).getApplications() : service.getAll();
    }

    public void initProgram(){
        program = programUuid != null ? service.get(programUuid) : new Program();
    }

    public void initProgrammer(){
        if (programmerUuid == null) return;
        programmer = programmerService.get(programmerUuid);
    }

    public String delete(Program program) {
        service.delete(program);
        return "programs?faces-redirect=true";
    }

    public String save() {
        program.setAuthor(programmerService.get(programmerUuid));
        service.save(program);
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

