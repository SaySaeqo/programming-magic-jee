package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ProgrammerViewController implements Serializable {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private ProgrammerService programmerService;

    @EJB
    public void setProgrammerService(ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    public ProgrammerViewController() {}


    public List<Programmer> getProgrammers() {
        return programmerService.getAll();
    }

    public String delete(Programmer programmer) {
        programmerService.delete( programmer);
        return "programmers?faces-redirect=true";
    }

}
