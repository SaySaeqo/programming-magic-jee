package pl.edu.pg.eti.kask.s180171.programmingmagic

import jakarta.inject.Inject
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.MultipartConfig
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerDto
import pl.edu.pg.eti.kask.s180171.programmingmagic.exception.UrlMappingNotFoundException

import java.util.regex.Pattern

@WebServlet(urlPatterns = "/*")
@MultipartConfig(maxFileSize = 100_000) // 250*400
class MainServlet extends HttpServlet {

    Logger log = LoggerFactory.getLogger(this.class.simpleName)

    ProgrammerService programmerService

    @Inject
    MainServlet(ProgrammerService programmerService){
        this.programmerService = programmerService
    }

    void init() {

//        def dataStore = servletContext.getAttribute("datasource") as DataStore
//        def imagesPath = servletContext.getInitParameter("imagesPath") as String
//        def fileSystemController = new FileSystemController(imagesPath)
//        programmerController = new ProgrammerController(new ProgrammerRepository(dataStore, fileSystemController))
    }

    static class Patterns{

        static final UUID = Pattern.compile "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}"

        static final PROGRAMMERS = Pattern.compile "/programmers/?"

        static final PROGRAMMER = Pattern.compile "/programmer/${this.UUID.pattern()}/?"

        static final PROGRAMMER_PORTRAIT = Pattern.compile "${PROGRAMMER.pattern()}/portrait/?"
    }

    @Override
    void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        try {
            String path = request.pathInfo ?: ""
            switch (path) {
                case { it.matches(Patterns.PROGRAMMERS) }:
                    response.contentType = "application/json"
                    response.writer.write ProgrammerDto.getStandardInfo(programmerService.getAll())
                    break
                case { it.matches(Patterns.PROGRAMMER) }:
                    response.contentType = "application/json"
                    def uuid = UUID.fromString path.find(Patterns.UUID)
                    def programmer = programmerService.get(uuid)
                    response.writer.write ProgrammerDto.getStandardInfo(programmer)
                    break
                case { it.matches(Patterns.PROGRAMMER_PORTRAIT) }:
                    response.contentType = "image/png"
                    def uuid = UUID.fromString path.find(Patterns.UUID)
                    def programmer = programmerService.get(uuid)
                    def portrait = programmerService.getPortrait(programmer)
                    response.contentLength = portrait.length
                    response.outputStream.write portrait
                    break
                default:
                    throw new UrlMappingNotFoundException(path, "GET")
            }
        }catch (HttpRequestException e){
            response.sendError e.responseCode, e.message
            return
        }

        response.status = HttpServletResponse.SC_OK
    }

    @Override
    void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = request.pathInfo ?: ""
            switch (path) {
                case { it.matches(Patterns.PROGRAMMER_PORTRAIT) }:
                    def uuid = UUID.fromString path.find(Patterns.UUID)
                    def programmer = programmerService.get(uuid)
                    programmerService.deletePortrait(programmer)
                    break
                default:
                    throw new UrlMappingNotFoundException(path, "DELETE")
            }
        }catch (HttpRequestException e){
            response.sendError e.responseCode, e.message
            return
        }

        response.status = HttpServletResponse.SC_OK
    }

    @Override
    void doPut(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        try {
            String path = request.pathInfo ?: ""
            switch (path) {
                case { it.matches(Patterns.PROGRAMMER_PORTRAIT) }:
                    def uuid = UUID.fromString path.find(Patterns.UUID)
                    def programmer = programmerService.get(uuid)
                    programmerService.savePortrait(request.getPart("portrait").inputStream, programmer)
                    break
                default:
                    throw new UrlMappingNotFoundException(path, "PUT")
            }
        }catch (HttpRequestException e){
            response.sendError e.responseCode, e.message
            return
        }

        response.status = HttpServletResponse.SC_OK
    }


    void destroy() {
    }
}