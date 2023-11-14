package pl.edu.pg.eti.kask.s180171.programmingmagic.view

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService

@RequestScoped
@Named
class ProgrammerViewController {

    ProgrammerService programmerService

    @Inject
    ProgrammerViewController(ProgrammerService programmerService) {
        this.programmerService = programmerService
    }

    List<Programmer> getProgrammers() {
        programmerService.all
    }

    String delete(Programmer programmer) {
        programmerService.getApplications(programmer)
        programmerService.cascadeDelete(programmer)
        return "programmers?faces-redirect=true"
    }

}
