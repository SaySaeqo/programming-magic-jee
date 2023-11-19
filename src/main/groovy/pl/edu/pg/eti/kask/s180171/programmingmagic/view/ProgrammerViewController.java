package pl.edu.pg.eti.kask.s180171.programmingmagic.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer;
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService;

import java.util.List;

@RequestScoped
@Named
public class ProgrammerViewController {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    ProgrammerService programmerService;

    @Inject
    public ProgrammerViewController(ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    public List<Programmer> getProgrammers() {
        return programmerService.getAll();
    }

    public String delete(Programmer programmer) {
        programmerService.delete( programmer);
        return "programmers?faces-redirect=true";
    }

}
