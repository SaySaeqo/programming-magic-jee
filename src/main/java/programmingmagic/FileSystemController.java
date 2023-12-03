package programmingmagic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ApplicationScoped
public class FileSystemController {
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    private String dirPath;

    public FileSystemController() {}

    @Inject
    public FileSystemController(HttpServletRequest request) {
        this.dirPath = request.getServletContext().getInitParameter("imagesPath");
    }

    public synchronized void save(byte[] image, String fileName) throws IOException {
        Path path = Paths.get(dirPath, fileName);
        try (OutputStream output = Files.newOutputStream(path)) {
            output.write(image);
        }
    }

    public synchronized byte[] load(String fileName) throws IOException {
        Path path = Paths.get(dirPath, fileName);
        try (InputStream input = Files.newInputStream(path)) {
            return input.readAllBytes();
        }
    }

    public synchronized void delete(String fileName) throws IOException {
        Path path = Paths.get(dirPath, fileName);
        Files.delete(path);
    }
}


