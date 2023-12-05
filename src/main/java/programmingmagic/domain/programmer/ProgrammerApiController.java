package programmingmagic.domain.programmer;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programmingmagic.domain.program.Program;
import programmingmagic.domain.program.ProgramDto;
import programmingmagic.domain.program.ProgramService;
import programmingmagic.security.UserRoles;

import java.io.InputStream;
import java.util.UUID;


@Path("programmer")
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
public class ProgrammerApiController {

    private Logger log = LoggerFactory.getLogger(getClass().getSimpleName());
    private ProgrammerService programmerService;
    private ProgramService programService;

    public ProgrammerApiController(){}

    @EJB
    public void setProgrammerService(ProgrammerService programmerService){
        this.programmerService = programmerService;
    }

    @EJB
    public void setProgramService(ProgramService programService){
        this.programService = programService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRoles.USER)
    public ProgrammerDto.ProgrammersModel getAllProgrammers(){
        return ProgrammerDto.getStandardInfo(programmerService.getAll());
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgrammerDto.ProgrammerModel getProgrammer(@PathParam("uuid") UUID uuid){
        Programmer programmer = programmerService.get(uuid);
        return ProgrammerDto.getStandardInfo(programmer);
    }

    @GET
    @Path("{uuid}/portrait")
    @Produces("image/png")
    public byte[] getProgrammerPortrait(@PathParam("uuid") UUID uuid){
        Programmer programmer = programmerService.get(uuid);
        return programmerService.getPortrait(programmer);
    }

    @GET
    @Path("{uuid}/program")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramDto.ProgramsModel getProgrammerPrograms(@PathParam("uuid") UUID uuid){
        Programmer programmer = programmerService.get(uuid);
        return ProgramDto.getStandardInfo(programmer.getApplications());
    }

    @DELETE
    @Path("{uuid}/portrait")
    public void deleteProgrammerPortrait(@PathParam("uuid") UUID uuid){
        Programmer programmer = programmerService.get(uuid);
        programmerService.deletePortrait(programmer);
    }

    @RolesAllowed(UserRoles.ADMIN)
    @DELETE
    @Path("{uuid}")
    public void deleteProgrammer(@PathParam("uuid") UUID uuid){
        Programmer programmer = programmerService.get(uuid);
        programmerService.delete(programmer);
    }

    @PUT
    @Path("{uuid}/portrait")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void putProgrammerPortrait(@PathParam("uuid") UUID uuid, @FormParam("portrait") InputStream portrait){
        Programmer programmer = programmerService.get(uuid);
        programmerService.savePortrait(portrait, programmer);
    }

    @POST
    @Path("{uuid}/program")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postProgram(@PathParam("uuid") UUID uuid, ProgramDto.ProgramModel program){
        Programmer programmer = programmerService.get(uuid);
        return programService.save(new Program(
                program.getName(),
                program.getDescription(),
                program.getCode(),
                program.getDateOfCreation(),
                programmer
        )).getUuid().toString();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postProgrammer(ProgrammerDto.ProgrammerModel programmer){
        return programmerService.save(new Programmer(
                programmer.getName(),
                programmer.getBirthday(),
                programmer.getLevel()
        )).getUuid().toString();
    }

    @PATCH
    @Path("{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void patchProgrammer(@PathParam("uuid") UUID uuid, ProgrammerDto.ProgrammerModel programmer){
        Programmer programmerToUpdate = programmerService.get(uuid);
        if (programmer.getName() != null) programmerToUpdate.setName(programmer.getName());
        if (programmer.getTitle() != null) programmerToUpdate.setTitle(programmer.getTitle());
        if (programmer.getBirthday() != null) programmerToUpdate.setBirthday(programmer.getBirthday());
        if (programmer.getLevel() != null) programmerToUpdate.setLevel(programmer.getLevel());
        programmerService.save(programmerToUpdate);
    }
}