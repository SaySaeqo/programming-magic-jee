package pl.edu.pg.eti.kask.s180171.programmingmagic.listener

import groovy.transform.CompileStatic
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.Initialized
import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.context.control.RequestContextController
import jakarta.enterprise.event.Observes
import jakarta.inject.Inject
import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.FileSystemController
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer

import java.nio.file.Path
import java.nio.file.Paths

@CompileStatic
@ApplicationScoped
class InitializeDataStore{

    Logger log = LoggerFactory.getLogger(this.class.simpleName)

    DataStore dataStore

    @Inject
    InitializeDataStore(DataStore dataStore){
        this.dataStore = dataStore
    }

    void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object initWhy) {
        init()
    }

    void init() {
        def testUUID = UUID.fromString("b36b52a6-552d-4fc2-8e64-644044d1dcb9")

        dataStore.with {
            addTable Programmer
            saveEntity new Programmer()
            saveEntity new Programmer()
            saveEntity new Programmer()
            saveEntity new Programmer()
            saveEntity new Programmer(
                    uuid: testUUID,
                    name: "Andrzej"
            )
        }

        def projectDir = "C:/Users/SaySaeqo/IdeaProjects/programming-magic-4"
        def testSubDir = "src/main/resources/pl/edu/pg/eti/kask/s180171/programmingmagic/portrait"
        def fullPath = Paths.get (projectDir,testSubDir).toString()
        def portrait = new FileSystemController().with {
            dirPath = fullPath
            load("andrzej.png")
        }

        log.info "Initilizing testing images from '$fullPath'"

        new FileSystemController().with {
            dirPath = Paths.get(projectDir, "portraits").toString()
            save(portrait, testUUID.toString() + ".png")
        }
    }
}
