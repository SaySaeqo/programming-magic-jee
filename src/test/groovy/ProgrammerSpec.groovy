import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel
import spock.lang.Specification

class ProgrammerSpec extends Specification{

    private Programmer withDefaults(){
        new Programmer().tap {
            name = "Andrzej"
            title = "Blysk switu"
            birthday = new Date()
            level = ProgrammerLevel.ROOKIE
            applications = []
        }
    }

    def "Programmer is deep copied"(){
        setup:
            Programmer programmer = withDefaults()
        when:
            Programmer copy = programmer.clone() as Programmer
        then:
            copy != programmer
            ! copy.uuid.is(programmer.uuid)
            ! copy.name.is(programmer.name)
    }
}
