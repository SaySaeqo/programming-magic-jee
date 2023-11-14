package pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program

import groovy.json.JsonGenerator
import groovy.transform.TupleConstructor
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology

import java.time.LocalDate

class ProgramDto {

    static generator = new JsonGenerator.Options().with {
        addConverter(LocalDate){it.format("yyyy-MM-DD")}
        build()
    }

    @TupleConstructor
    static class ProgramModel{
        String name
        String description
        String code
        LocalDate dateOfCreation

        ProgramModel(Program other){
            properties.each {
                try {
                    this."$it.key" = other."$it.key"
                } catch (ReadOnlyPropertyException ignored){}
            }
        }

        ProgramModel(){}
    }

    static class ProgramsModel{
        List<ProgramModel> programs = []

        ProgramsModel(List<Program> programs){
            programs.each {
                this.programs << new ProgramModel(it)
            }

        }
    }

    static String getStandardInfo(Program program){ generator.toJson new ProgramModel(program) }
    static String getStandardInfo(List<Program> programs){ generator.toJson new ProgramsModel(programs)}
}
