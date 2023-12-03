//package pl.edu.pg.eti.kask.s180171.programmingmagic.view
//
//import jakarta.enterprise.context.RequestScoped
//import jakarta.inject.Inject
//import jakarta.inject.Named
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
//import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService
//
//@RequestScoped
//@Named
//class ProgrammerViewController {
//
//    Logger log = LoggerFactory.getLogger(ProgrammerViewController.class.simpleName)
//
//    ProgrammerService programmerService
//
//    @Inject
//    ProgrammerViewController(ProgrammerService programmerService) {
//        this.programmerService = programmerService
//    }
//
//    List<Programmer> getProgrammers() {
//        log.info "a"
//        programmerService.getAll()
//    }
//
//    String delete(Programmer programmer) {
//        programmerService.delete programmer
//        return "programmers?faces-redirect=true"
//    }
//
//}
