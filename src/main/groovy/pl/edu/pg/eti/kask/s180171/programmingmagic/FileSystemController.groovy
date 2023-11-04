package pl.edu.pg.eti.kask.s180171.programmingmagic

import jakarta.enterprise.context.ApplicationScoped
import jakarta.faces.annotation.InitParameterMap
import jakarta.inject.Inject
import jakarta.servlet.ServletContext
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.ws.rs.core.Context
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.nio.file.Files
import java.nio.file.Paths

@ApplicationScoped
class FileSystemController {

    Logger log = LoggerFactory.getLogger(this.class.simpleName)

    String dirPath

    FileSystemController(){}

    @Inject
    FileSystemController(HttpServletRequest request){
        this.dirPath = request.servletContext.getInitParameter("imagesPath") as String
    }

    synchronized void save(byte[]  image, String fileName) throws IOException{
        def path = Paths.get(dirPath, fileName)
        try (def out = Files.newOutputStream(path)){
            out.write image
        }
    }

    synchronized byte[] load(String fileName) throws IOException{
        def path = Paths.get(dirPath, fileName)
        try (def input = Files.newInputStream(path)){
            return input.readAllBytes()
        }
    }

    synchronized void delete(String fileName) throws IOException{
        def path = Paths.get(dirPath, fileName)
        Files.delete(path)
    }
}
