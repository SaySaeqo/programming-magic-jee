package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.FormParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("program")
class ProgramApiController {

    ProgramService programService

    @Inject
    ProgramApiController(ProgramService programService){
        this.programService = programService
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getAllPrograms(){
        ProgramDto.getStandardInfo(programService.getAll())
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    String getProgram(@PathParam("uuid") UUID uuid){
        def program = programService.get(uuid)
        ProgramDto.getStandardInfo(program)
    }

    @DELETE
    @Path("{uuid}")
    void deleteProgram(@PathParam("uuid") UUID uuid){
        def program = programService.get(uuid)
        programService.delete(program)
    }

    @PATCH
    @Path("{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateProgram(@PathParam("uuid") UUID uuid, ProgramDto.ProgramModel program){
        def programToUpdate = programService.get(uuid).tap {
            name = program.name?: name
            description = program.description?: description
            code = program.code?: code
            dateOfCreation = program.dateOfCreation?: dateOfCreation
        }
        programService.save(programToUpdate)
    }

}
