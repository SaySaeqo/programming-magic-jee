package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.servlet.http.HttpServletResponse
import pl.edu.pg.eti.kask.s180171.programmingmagic.FileSystemController
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException

@RequestScoped
class ProgrammerRepository extends BaseRepository<Programmer>{

    FileSystemController fileSystemController

    ProgrammerRepository(){super()}
    @Inject
    ProgrammerRepository(FileSystemController fileSystemController) {
        super(Programmer.class)
        this.fileSystemController = fileSystemController
    }

    @PersistenceContext
    void setEntityManager(EntityManager entityManager){
        this._entityManager = entityManager
    }

    void savePortrait(InputStream inputStream, Programmer forProgrammer){
        try{
            fileSystemController.save(inputStream.readAllBytes(), forProgrammer.uuid.toString()+".png")
        } catch (IOException e){

            throw new HttpRequestException(HttpServletResponse.SC_BAD_REQUEST, "Cannot save image. Try again.\n" +
                    e.localizedMessage +"\n"+
                    e.message)
        }
    }

    byte[] getPortrait(Programmer forProgrammer){
        try{
            fileSystemController.load(forProgrammer.uuid.toString()+".png")
        } catch (IOException ignored){
            throw new HttpRequestException(
                    HttpServletResponse.SC_NOT_FOUND,
                    "There is no portrait for programmer with uuid $forProgrammer.uuid"
            )
        }
    }

    void deletePortrait(Programmer forProgrammer){
        try{
            fileSystemController.delete(forProgrammer.uuid.toString()+".png")
        } catch (IOException ignored){
            throw new HttpRequestException(
                    HttpServletResponse.SC_NOT_FOUND,
                    "There is no portrait for programmer with uuid $forProgrammer.uuid"
            )
        }
    }

    List<Programmer> findByName(String name){
        _entityManager.createQuery("SELECT p FROM Programmer p WHERE p.name = :name")
                .setParameter("name", name)
                .resultList
    }

}
