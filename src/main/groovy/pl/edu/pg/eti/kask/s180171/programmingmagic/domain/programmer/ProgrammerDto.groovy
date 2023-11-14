package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer

import groovy.json.JsonGenerator
import groovy.transform.TupleConstructor
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel

import java.time.LocalDate

class ProgrammerDto {

    static generator = new JsonGenerator.Options().with {
        addConverter(LocalDate){it.format("yyyy-MM-DD")}
        build()
    }

    @TupleConstructor
    static class ProgrammerModel {
        UUID uuid
        String name
        String title
        LocalDate birthday
        ProgrammerLevel level

        ProgrammerModel(Programmer other){
            properties.each {
                try {
                    this."$it.key" = other."$it.key"
                } catch (ReadOnlyPropertyException ignored){}
            }
        }

        ProgrammerModel(){}
    }

    static class ProgrammersModel {
        List<ProgrammerModel> programmers = []

        ProgrammersModel(List<Programmer> programmers){
            programmers.each {
                this.programmers << new ProgrammerModel(it)
            }

        }
    }

    static String getStandardInfo(Programmer programmer){ generator.toJson new ProgrammerModel(programmer) }
    static String getStandardInfo(List<Programmer> programmers){ generator.toJson new ProgrammersModel(programmers)}

}
