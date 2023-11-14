package pl.edu.pg.eti.kask.s180171.programmingmagic

import groovy.transform.CompileStatic
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.Initialized
import jakarta.enterprise.context.control.RequestContextController
import jakarta.enterprise.event.Observes
import jakarta.inject.Inject
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pl.edu.pg.eti.kask.s180171.programmingmagic.DataStore
import pl.edu.pg.eti.kask.s180171.programmingmagic.FileSystemController
import pl.edu.pg.eti.kask.s180171.programmingmagic.base.HttpRequestException
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.Program
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.program.ProgramService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.Programmer
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerRepository
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.ProgrammerService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programmer.model.ProgrammerLevel
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.ProgrammingLanguage
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.ProgrammingLanguageService
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.programminglanguage.model.ProgrammingLanguageType
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.Technology
import pl.edu.pg.eti.kask.s180171.programmingmagic.domain.technology.TechnologyService

import java.nio.file.Paths
import java.time.LocalDate

@CompileStatic
@ApplicationScoped
class DataStoreInitializer {

    Logger log = LoggerFactory.getLogger(this.class.simpleName)

    RequestContextController requestContextController
    ProgramService programService
    ProgrammerService programmerService
    ProgrammingLanguageService programmingLanguageService
    TechnologyService technologyService

    @Inject
    DataStoreInitializer(RequestContextController requestContextController,
                         ProgramService programService,
                         ProgrammerService programmerService,
                         ProgrammingLanguageService programmingLanguageService,
                         TechnologyService technologyService){
        this.requestContextController = requestContextController
        this.programmerService = programmerService
        this.programService = programService
        this.programmingLanguageService = programmingLanguageService
        this.technologyService = technologyService
    }

    // albo też bez ApplicationScoped jako zwykła klasa, która byłaby tworzona i wywoływana w servlecie w metodzie init
    void init(@Observes @Initialized(ApplicationScoped.class) Object init) {

        log.info "Initializing data store"

        requestContextController.activate()

        final testUUID = UUID.fromString("b36b52a6-552d-4fc2-8e64-644044d1dcb9")

        final projectDir = "C:/Users/SaySaeqo/IdeaProjects/programming-magic-4"
        final testSubDir = "src/main/resources/pl/edu/pg/eti/kask/s180171/programmingmagic/portrait"
        final fullPath = Paths.get (projectDir,testSubDir).toString()

        programmerService.with {
            save new Programmer(
                    uuid: testUUID,
                    name: "Andrzej",
                    birthday: LocalDate.of(1999, 1, 1),
                    level: ProgrammerLevel.JUNIOR,

            )
            save new Programmer(
                    name: "Bartek",
                    birthday: LocalDate.of(1998, 1, 1),
                    level: ProgrammerLevel.ROOKIE,
            )
            save new Programmer(
                    name: "Ania",
                    birthday: LocalDate.of(1997, 1, 1),
                    level: ProgrammerLevel.MASTER,
            )
            save new Programmer(
                    name: "Karolina",
                    birthday: LocalDate.of(1996, 1, 1),
                    level: ProgrammerLevel.SENIOR,
            )
            save new Programmer(
                    name: "Edward",
                    birthday: LocalDate.of(1995, 1, 1),
                    level: ProgrammerLevel.SCHOLAR,
            )

        }

        Map<UUID, byte[]> portraits = [:]
        // iterate throuth all files in directory
        new File(fullPath).eachFile { file ->

            def fileName = file.name
            def fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1)
            if (fileExtension != "png") return
            def fileContent = file.bytes

            def fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'))
            String programmerName = fileNameWithoutExtension.capitalize()
            try {
                def programmerUuid = programmerService.getByName(programmerName).uuid
                portraits.put programmerUuid, fileContent
            } catch (HttpRequestException ignored) {
                log.error "Programmer with name $programmerName not found"
                return
            }
        }
        new FileSystemController().with {
            dirPath = Paths.get(projectDir, "portraits").toString()
            portraits.each { uuid, portrait ->
                save portrait, uuid.toString() + ".png"
            }
        }

        programService.with {
            save new Program(
                    name: "NWD",
                    description: "Największy wspólny dzielnik",
                    code: "int nwd(int a, int b){\n" +
                            "    if (b == 0) return a;\n" +
                            "    return nwd(b, a % b);\n" +
                            "}",
                    dateOfCreation: LocalDate.of(2021, 1, 1),
                    author: programmerService.get(testUUID),
            )
            save new Program(
                    name: "Minimum",
                    description: "Znajdowanie minimum z tablicy",
                    code: "int min(int[] tab){\n" +
                            "    int min = tab[0];\n" +
                            "    for (int i = 1; i < tab.length; i++) {\n" +
                            "        if (tab[i] < min) min = tab[i];\n" +
                            "    }\n" +
                            "    return min;\n" +
                            "}",
                    dateOfCreation: LocalDate.of(2021, 1, 2),
                    author: programmerService.get(testUUID),
            )
            save new Program(
                    name: "Snake",
                    description: "Gra w snake'a",
                    code: "...",
                    dateOfCreation: LocalDate.of(2021, 1, 3),
                    author: programmerService.get(testUUID),
            )
            save new Program(
                    name: "Tetris",
                    description: "Gra w tetrisa",
                    code: "...",
                    dateOfCreation: LocalDate.of(2021, 1, 4),
                    author: programmerService.get(programmerService.getByName("Bartek").uuid),
            )
        }

        programmingLanguageService.with {
            save new ProgrammingLanguage(
                    name: "Java",
                    type: ProgrammingLanguageType.HIGH_LEVEL,
                    isCompiled: true,
                    isStrongTyped: true
            )
            save new ProgrammingLanguage(
                    name: "C++",
                    type: ProgrammingLanguageType.LOW_LEVEL,
                    isCompiled: true,
                    isStrongTyped: true
            )
            save new ProgrammingLanguage(
                    name: "Python",
                    type: ProgrammingLanguageType.HIGH_LEVEL,
                    isCompiled: false,
                    isStrongTyped: false
            )
            save new ProgrammingLanguage(
                    name: "assembler",
                    type: ProgrammingLanguageType.BYTECODE,
                    isCompiled: true,
                    isStrongTyped: false
            )
        }

        technologyService.with {
            save new Technology(
                    name: "Spring",
                    isFramework: true,
                    dateOfCreation: LocalDate.of(2004, 1, 1)
            )
            save new Technology(
                    name: "Grails",
                    isFramework: true,
                    dateOfCreation: LocalDate.of(2008, 1, 1)
            )
            save new Technology(
                    name: "React",
                    isFramework: false,
                    dateOfCreation: LocalDate.of(2013, 1, 1)
            )
            save new Technology(
                    name: "PyGame",
                    isFramework: false,
                    dateOfCreation: LocalDate.of(2000, 1, 1)
            )
        }

        requestContextController.deactivate()
    }
}
