package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.json.JsonGenerator
import groovy.json.JsonOutput
import groovy.transform.TupleConstructor
import jakarta.json.stream.JsonGeneratorFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer as entityProgrammer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel

import java.time.LocalDate

class ProgrammerDto {

    static generator = new JsonGenerator.Options().with {
        addConverter(LocalDate){it.format("yyyy-MM-DD")}
        build()
    }

    @TupleConstructor
    static class Programmer{
        UUID uuid
        String name
        String title
        LocalDate birthday
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

    static String getStandardInfo(entityProgrammer programmer){ generator.toJson new Programmer(programmer) }
    static String getStandardInfo(List<entityProgrammer> programmers){ generator.toJson new Programmers(programmers)}

}
