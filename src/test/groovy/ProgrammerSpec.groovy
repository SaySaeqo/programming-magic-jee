import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel
import spock.lang.Specification

import java.time.LocalDate

class ProgrammerSpec extends Specification{


    DataStore dataStore
    ProgrammerRepository programmerRepository
    ProgrammerService programmerService

    def setup(){
        dataStore = new DataStore()
        programmerRepository = new ProgrammerRepository(dataStore, null)
        programmerService = new ProgrammerService(programmerRepository)
    }

    private Programmer withDefaults(){
        new Programmer().tap {
            name = "Andrzej"
            title = "Blysk switu"
            birthday = LocalDate.now()
            level = ProgrammerLevel.ROOKIE
            applications = []
        }
    }

    private Program withDefaults2(){
        new Program().tap {
            name = "Program"
            description = "Description"
            author = withDefaults()
        }
    }

    def "Programmer is deep copied"(){
        setup:
            Programmer programmer = withDefaults()
        when:
            Programmer copy = programmer.clone() as Programmer
        then:
            copy == programmer
            ! copy.is(programmer)
            ! copy.uuid.is(programmer.uuid)
            ! copy.name.is(programmer.name)
    }

//    def "Programmer set is deep copied"(){
//        setup:
//            Programmer programmer = withDefaults()
//            HashSet<Programmer> programmers = new HashSet<Programmer>()
//            programmers.add(programmer)
//        when:
//            HashSet<Programmer> copy = programmers.clone as HashSet<Programmer>
//        then:
//            copy == programmers
//            copy.size() == 1
//            copy.first() == programmer
//            !(copy.first().is programmer)
//
//    }

    def "Program is deep copied"(){
        setup:
            Program program = withDefaults2()
        when:
            Program copy = program.clone() as Program
        then:
            copy == program
            ! copy.is(program)
            ! copy.uuid.is(program.uuid)
            ! copy.name.is(program.name)
            ! copy.author.is(program.author)
            copy.author == program.author
    }

    def "Program is saved with linking"(){
        setup:
            Program program = withDefaults2()
            Programmer programmer = program.author
            Programmer programmer2 = withDefaults()
            programmer2.name = "Barbara"
            programmer2.uuid = programmer.uuid
        when:
            Program savedProgram = dataStore.save(program)
            Programmer savedProgrammer = dataStore.save(programmer2)
            Program currentProgram = dataStore.getCopyOfTable(Program).first()
        then:
            ! currentProgram.is(savedProgram)
            savedProgram.author.name != savedProgrammer.name
            currentProgram.author.name == savedProgrammer.name
            ! savedProgram.is(program)
            ! savedProgram.author.is(programmer)
            ! currentProgram.is(program)
            ! currentProgram.author.is(programmer2)
    }

    def "Applications linking works"(){
        setup:
            // add few programs with same author
            Program program = withDefaults2()
            Program program2 = withDefaults2()
            Program program3 = withDefaults2()
            program2.name = "Program2"
            program3.name = "Program3"
            program2.author = program.author
            program3.author = program.author

        when:
            dataStore.save(program)
            dataStore.save(program2)
            dataStore.save(program3)
            Programmer programmer = programmerService.get(program.author.uuid)
            programmerRepository.linkToList(Program, "applications", programmer)
        then:
            programmer.applications.size() == 3
            programmer.applications.contains(program)
            programmer.applications.contains(program2)
            programmer.applications.contains(program3)

    }

    def "Applications are deleted by cascade"(){
        setup:
            // add few programs with same author
            Program program = withDefaults2()
            Program program2 = withDefaults2()
            Program program3 = withDefaults2()
            program2.name = "Program2"
            program3.name = "Program3"
            program2.author = program.author
            program3.author = program.author

        when:
            dataStore.save(program)
            dataStore.save(program2)
            dataStore.save(program3)
            Programmer programmer = programmerService.get(program.author.uuid)
            programmerRepository.linkToList(Program, "applications", programmer)
            programmerService.cascadeDelete(programmer)
        then:
            dataStore.getCopyOfTable(Program).size() == 0
            dataStore.getCopyOfTable(Programmer).size() == 0
    }
}
