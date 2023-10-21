package pl.edu.pg.eti.kask.s180171.programmingmagic.listener

import jakarta.servlet.ServletContextEvent
import jakarta.servlet.ServletContextListener
import jakarta.servlet.annotation.WebListener
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore

@WebListener
class CreateDataStore implements ServletContextListener {

    @Override
    void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("datasource", new DataStore())
    }
}
