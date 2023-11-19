import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel
import spock.lang.Specification

import java.time.LocalDate

class ProgrammerSpec extends Specification{

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
}
