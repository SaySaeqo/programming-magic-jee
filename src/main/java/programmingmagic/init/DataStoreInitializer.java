package programmingmagic.init;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import programmingmagic.FileSystemController;
import programmingmagic.domain.program.Program;
import programmingmagic.domain.program.ProgramService;
import programmingmagic.domain.programmer.Programmer;
import programmingmagic.domain.programmer.ProgrammerService;
import programmingmagic.domain.programmer.model.ProgrammerLevel;
import programmingmagic.domain.programminglanguage.ProgrammingLanguage;
import programmingmagic.domain.programminglanguage.ProgrammingLanguageService;
import programmingmagic.domain.programminglanguage.model.ProgrammingLanguageType;
import programmingmagic.domain.technology.Technology;
import programmingmagic.domain.technology.TechnologyService;
import programmingmagic.security.User;
import programmingmagic.security.UserRoles;
import programmingmagic.security.UserService;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@DependsOn("InitializeAdminService")
@DeclareRoles({UserRoles.ADMIN, UserRoles.USER})
@RunAs(UserRoles.ADMIN)
@NoArgsConstructor
public class DataStoreInitializer {

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    ProgramService programService;
    ProgrammerService programmerService;
    ProgrammingLanguageService programmingLanguageService;
    TechnologyService technologyService;
    UserService userService;
    Pbkdf2PasswordHash passwordHash;


    @Inject
    public DataStoreInitializer(Pbkdf2PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    @EJB
    public void setProgrammerService(ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    @EJB
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @EJB
    public void setProgrammingLanguageService(ProgrammingLanguageService programmingLanguageService) {
        this.programmingLanguageService = programmingLanguageService;
    }

    @EJB
    public void setTechnologyService(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @EJB
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // albo też bez @Startup jako zwykła klasa, która byłaby tworzona i wywoływana w servlecie w metodzie init
    @PostConstruct
    public void init() {

        log.info ("Initializing data store");


        Programmer programmer1 = new Programmer("Andrzej", LocalDate.of(1999, 1, 1), ProgrammerLevel.JUNIOR);
        save(programmer1);

        Programmer programmer2 = new Programmer("Bartek", LocalDate.of(1998, 1, 1), ProgrammerLevel.ROOKIE);
        save(programmer2);

        Programmer programmer3 = new Programmer("Ania", LocalDate.of(1997, 1, 1), ProgrammerLevel.MASTER);
        save(programmer3);

        Programmer programmer4 = new Programmer("Karolina", LocalDate.of(1996, 1, 1), ProgrammerLevel.SENIOR);
        save(programmer4);

        Programmer programmer5 = new Programmer("Edward", LocalDate.of(1995, 1, 1), ProgrammerLevel.SCHOLAR);
        save(programmer5);


        String projectDir = "/home/saysaeqo/javaProjects/programming-magic-4";
        String testSubDir = "src/main/resources/pl/edu/pg/eti/kask/s180171/programmingmagic/portrait";
        String fullPath = Paths.get (projectDir,testSubDir).toString();

        // iterate throuth all files in directory
        String portraitDir = Paths.get(projectDir, "portraits").toString();
        File[] currentPortraits = new File(portraitDir).listFiles();
        if (currentPortraits == null) {
            log.error(String.format("Error while reading directory %s", portraitDir));
            return;
        }
        for (File file : currentPortraits){
            file.delete();
        }

        Map<UUID, byte[]> portraits = new HashMap<>();
        // iterate throuth all files in directory
        File[] files = new File(fullPath).listFiles();
        if (files == null) {
            log.error(String.format("Error while reading directory %s", fullPath));
            return;
        }
        for (File file : files){

            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (!fileExtension.equals("png")) continue;
            byte[] fileContent;
            try (InputStream inputStream = new FileInputStream(file)) {
                fileContent = inputStream.readAllBytes();
            } catch (IOException e) {
                log.error(String.format("Error while reading file %s", fileName));
                continue;
            }

            String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            String programmerName = fileNameWithoutExtension.substring(0, 1).toUpperCase() + fileNameWithoutExtension.substring(1);
            try {
                UUID programmerUuid = programmerService.getByName(programmerName).getUuid();
                if (programmerUuid != null) portraits.put(programmerUuid, fileContent);
            } catch (Exception ignored) {
                log.error(String.format("Programmer with name %s not found", programmerName));
            }
        }
        FileSystemController fsc = new FileSystemController();
        fsc.setDirPath(Paths.get(projectDir, "portraits").toString());
        portraits.forEach(((uuid, bytes) -> {
            try {
                fsc.save(bytes, uuid.toString()+ ".png");
            } catch (IOException e) {
                log.error(String.format("Pls no! %s", e));
            }
        }));

        Programmer testProgrammer = programmerService.getByName("Andrzej");
        Programmer testProgrammer2 = programmerService.getByName("Bartek");

        Program program1 = new Program(
                "NWD",
                "Największy wspólny dzielnik",
                "int nwd(int a, int b){\n" +
                        "    if (b == 0) return a;\n" +
                        "    return nwd(b, a % b);\n" +
                        "}",
                LocalDate.of(2021, 1, 1),
                testProgrammer
        );

        Program program2 = new Program(
                "Minimum",
                "Znajdowanie minimum z tablicy",
                "int min(int[] tab){\n" +
                        "    int min = tab[0];\n" +
                        "    for (int i = 1; i < tab.length; i++) {\n" +
                        "        if (tab[i] < min) min = tab[i];\n" +
                        "    }\n" +
                        "    return min;\n" +
                        "}",
                LocalDate.of(2021, 1, 2),
                testProgrammer
        );

        Program program3 = new Program(
                "Snake",
                "Gra w snake'a",
                "...",
                LocalDate.of(2021, 1, 3),
                testProgrammer2
        );

        Program program4 = new Program(
                "Tetris",
                "Gra w tetrisa",
                "...",
                LocalDate.of(2021, 1, 4),
                testProgrammer2
        );

        ProgrammingLanguage language1 = new ProgrammingLanguage(
                "Java",
                ProgrammingLanguageType.HIGH_LEVEL,
                true,
                true
        );

        ProgrammingLanguage language2 = new ProgrammingLanguage(
                "C++",
                ProgrammingLanguageType.LOW_LEVEL,
                true,
                true
        );

        ProgrammingLanguage language3 = new ProgrammingLanguage(
                "Python",
                ProgrammingLanguageType.HIGH_LEVEL,
                false,
                false
        );

        ProgrammingLanguage language4 = new ProgrammingLanguage(
                "assembler",
                ProgrammingLanguageType.BYTECODE,
                true,
                false
        );

        Technology technology1 = new Technology(
                "Spring",
                true,
                LocalDate.of(2004, 1, 1)
        );

        Technology technology2 = new Technology(
                "Grails",
                true,
                LocalDate.of(2008, 1, 1)
        );

        Technology technology3 = new Technology(
                "React",
                false,
                LocalDate.of(2013, 1, 1)
        );

        Technology technology4 = new Technology(
                "PyGame",
                false,
                LocalDate.of(2000, 1, 1)
        );

        User user1 = User.builder()
                .login("adm") // DLACZEGO NIE MOŻE BYĆ "admin"?!?!
                .password(passwordHash.generate("admin".toCharArray()))
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User user2 = User.builder()
                .login("user")
                .password(passwordHash.generate("user".toCharArray()))
                .roles(List.of(UserRoles.USER))
                .build();


        save(program1);
        save(program2);
        save(program3);
        save(program4);
        save(language1);
        save(language2);
        save(language3);
        save(language4);
        save(technology1);
        save(technology2);
        save(technology3);
        save(technology4);
        save(user1);
        save(user2);

    }

    private void save(Programmer programmer) {
        programmerService.save(programmer);
    }

    private void save(Program program) {
        programService.save(program);
    }

    private void save(ProgrammingLanguage programmingLanguage) {
        programmingLanguageService.save(programmingLanguage);
    }

    private void save(Technology technology) {
        technologyService.save(technology);
    }

    private void save(User user) {
        userService.save(user);
    }
}
