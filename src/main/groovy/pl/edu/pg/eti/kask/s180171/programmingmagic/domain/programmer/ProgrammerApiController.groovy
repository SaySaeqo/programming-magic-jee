package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.transform.CompileStatic
import jakarta.ejb.EJB
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.FormParam
import jakarta.ws.rs.GET
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramDto
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramService

@Path("programmer")
@CompileStatic
class ProgrammerApiController {

    ProgrammerService programmerService
    ProgramService programService

    ProgrammerApiController(){}

    @EJB
    void setProgrammerService(ProgrammerService programmerService){
        this.programmerService = programmerService
    }

    @EJB
    void setProgramService(ProgramService programService){
        this.programService = programService
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getAllProgrammers(){
        ProgrammerDto.getStandardInfo(programmerService.getAll())
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    String getProgrammer(@PathParam("uuid") UUID uuid){
        def programmer = programmerService.get(uuid)
        ProgrammerDto.getStandardInfo(programmer)
    }

    @GET
    @Path("{uuid}/portrait")
    @Produces("image/png")
    byte[] getProgrammerPortrait(@PathParam("uuid") UUID uuid){
        Programmer programmer = programmerService.get(uuid)
        programmerService.getPortrait(programmer)
    }

    @GET
    @Path("{uuid}/program")
    @Produces(MediaType.APPLICATION_JSON)
    String getProgrammerPrograms(@PathParam("uuid") UUID uuid){
        def programmer = programmerService.get(uuid)
        ProgramDto.getStandardInfo(programmer.applications)
    }

    @DELETE
    @Path("{uuid}/portrait")
    void deleteProgrammerPortrait(@PathParam("uuid") UUID uuid){
        def programmer = programmerService.get(uuid)
        programmerService.deletePortrait(programmer)
    }

    @DELETE
    @Path("{uuid}")
    void deleteProgrammer(@PathParam("uuid") UUID uuid){
        def programmer = programmerService.get(uuid)
        programmerService.delete programmer
    }

    @PUT
    @Path("{uuid}/portrait")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    void putProgrammerPortrait(@PathParam("uuid") UUID uuid, @FormParam("portrait") InputStream portrait){
        def programmer = programmerService.get(uuid)
        programmerService.savePortrait(portrait, programmer)
    }

    @POST
    @Path("{uuid}/program")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    String postProgram(@PathParam("uuid") UUID uuid, ProgramDto.ProgramModel program){
        def programmer = programmerService.get(uuid)
        programService.save(new Program(
                name: program.name,
                description: program.description,
                dateOfCreation: program.dateOfCreation,
                author: programmer,
                code: program.code,
                usedTechnology: null
        )).uuid.toString()
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    String postProgrammer(ProgrammerDto.ProgrammerModel programmer){
        programmerService.save(new Programmer(
                name: programmer.name,
                title: programmer.title,
                birthday: programmer.birthday,
                level: programmer.level,
                applications: null
        )).uuid.toString()
    }

    @PATCH
    @Path("{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchProgrammer(@PathParam("uuid") UUID uuid, ProgrammerDto.ProgrammerModel programmer){
        def programmerToUpdate = programmerService.get(uuid).tap{
            name = programmer.name?: name
            title = programmer.title?: title
            birthday = programmer.birthday?: birthday
            level = programmer.level?: level
        }
        programmerService.save(programmerToUpdate)
    }


}
