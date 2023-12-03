package programmingmagic.domain.program;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.UUID;

@Path("program")
public class ProgramApiController {
    @Inject
    private ProgramService programService;

    public ProgramApiController() {}

    @EJB
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramDto.ProgramsModel getAllPrograms() {
        return ProgramDto.getStandardInfo(programService.getAll());
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramDto.ProgramModel getProgram(@PathParam("uuid") UUID uuid) {
        Program program = programService.get(uuid);
        return ProgramDto.getStandardInfo(program);
    }

    @DELETE
    @Path("{uuid}")
    public void deleteProgram(@PathParam("uuid") UUID uuid) {
        Program program = programService.get(uuid);
        programService.delete(program);
    }

    @PATCH
    @Path("{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgram(@PathParam("uuid") UUID uuid, ProgramDto.ProgramModel program) {
        Program programToUpdate = programService.get(uuid);
        if (program.getName() != null) {
            programToUpdate.setName(program.getName());
        }
        if (program.getDescription() != null) {
            programToUpdate.setDescription(program.getDescription());
        }
        if (program.getCode() != null) {
            programToUpdate.setCode(program.getCode());
        }
        if (program.getDateOfCreation() != null) {
            programToUpdate.setDateOfCreation(program.getDateOfCreation());
        }
        programService.save(programToUpdate);
    }
}


