package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.servlet.http.HttpServletResponse
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.FileSystemController
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.BaseRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program

@RequestScoped
class ProgrammerRepository extends BaseRepository<Programmer>{

    FileSystemController fileSystemController

    ProgrammerRepository(){super()}
    @Inject
    ProgrammerRepository(DataStore dataStore, FileSystemController fileSystemController) {
        super(dataStore, Programmer.class)
        this.fileSystemController = fileSystemController
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

}
