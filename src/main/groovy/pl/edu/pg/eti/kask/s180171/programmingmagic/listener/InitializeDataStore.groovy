package pl.edu.pg.eti.kask.s180171.programmingmagic.listener

import groovy.transform.CompileStatic
import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer

@CompileStatic
@WebListener
class InitializeDataStore implements ServletContextListener{

    @Override
    void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource")

        dataSource.with {
            addTable Programmer
            saveEntity new Programmer()
            saveEntity new Programmer()
            saveEntity new Programmer()
            saveEntity new Programmer()
            saveEntity new Programmer(
                    uuid: UUID.fromString("b36b52a6-552d-4fc2-8e64-644044d1dcb9"),
                    portrait: this.class.getResourceAsStream("../portrait/andrzej.png")?.readAllBytes(),
                    name: "Andrzej"
            )
        }

    }
}
