package programmingmagic.domain.programmer;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import programmingmagic.FileSystemController;
import programmingmagic.base.BaseRepository;
import programmingmagic.base.HttpRequestException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Dependent
public class ProgrammerRepository extends BaseRepository<Programmer> {
    private FileSystemController fileSystemController;

    public ProgrammerRepository() {
        super();
    }

    @Inject
    public ProgrammerRepository(FileSystemController fileSystemController) {
        super(Programmer.class);
        this.fileSystemController = fileSystemController;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void savePortrait(InputStream inputStream, Programmer forProgrammer) {
        try {
            fileSystemController.save(inputStream.readAllBytes(), forProgrammer.getUuid().toString() + ".png");
        } catch (IOException e) {
            throw new HttpRequestException(HttpServletResponse.SC_BAD_REQUEST, "Cannot save image. Try again.\n" +
                    e.getLocalizedMessage() + "\n" +
                    e.getMessage());
        }
    }

    public byte[] getPortrait(Programmer forProgrammer) {
        try {
            return fileSystemController.load(forProgrammer.getUuid().toString() + ".png");
        } catch (IOException ignored) {
            throw new HttpRequestException(
                    HttpServletResponse.SC_NOT_FOUND,
                    "There is no portrait for programmer with uuid " + forProgrammer.getUuid()
            );
        }
    }

    public void deletePortrait(Programmer forProgrammer) {
        try {
            fileSystemController.delete(forProgrammer.getUuid().toString() + ".png");
        } catch (IOException ignored) {
            throw new HttpRequestException(
                    HttpServletResponse.SC_NOT_FOUND,
                    "There is no portrait for programmer with uuid " + forProgrammer.getUuid()
            );
        }
    }

    public List<Programmer> findByName(String name) {
        return entityManager.createQuery("SELECT p FROM Programmer p WHERE p.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}


