package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.json.JsonOutput
import groovy.transform.TupleConstructor
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer as entityProgrammer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel

class ProgrammerDto {

    @TupleConstructor
    static class Programmer{
        UUID uuid
        String name
        String title
        Date birthday
        ProgrammerLevel level

        Programmer(entityProgrammer other){
            properties.each {
                try {
                    this."$it.key" = other."$it.key"
                } catch (ReadOnlyPropertyException ignored){}
            }
        }
    }

    static class Programmers{
        List<Programmer> programmers = []

        Programmers(List<entityProgrammer> programmers){
            programmers.each {
                this.programmers << new Programmer(it)
            }

        }
    }

    static String getStandardInfo(entityProgrammer programmer){ JsonOutput.toJson new Programmer(programmer) }
    static String getStandardInfo(List<entityProgrammer> programmers){ JsonOutput.toJson new Programmers(programmers)}

}
