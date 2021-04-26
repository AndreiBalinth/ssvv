package ssvv.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class TDITesting {
    private final String VALID_ID = "baie2466",
            VALID_ID_TEMA = "7", VALID_ID_NOTA = "1";

    private Service createService(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        return new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    private void removeStudentWithValidId(Service service){
        if(service.findStudent(VALID_ID) != null)
            service.deleteStudent(VALID_ID);
    }

    private void removeTemaWithValidId(Service service) {
        if(service.findTema(VALID_ID_TEMA) != null)
            service.deleteTema(VALID_ID_TEMA);
    }

    private void removeNotaWithValidId(Service service){
        if(service.findNota(VALID_ID_NOTA) != null)
            service.deleteNota(VALID_ID_NOTA);
    }

    @Test
    public void testAddStudent(){
        Service service = createService();
        removeStudentWithValidId(service);

        assertNull(
                service.addStudent(
                        new Student(VALID_ID, "Andrei", 10, "email@email.com")
                )
        );
    }

    @Test
    public void testAddStudentAndAddAssignmet(){
        Service service = createService();
        removeStudentWithValidId(service);
        removeTemaWithValidId(service);

        assertNull(
                service.addStudent(
                        new Student(VALID_ID, "Andrei", 10, "email@email.com")
                )
        );

        assertNull(
                service.addTema(
                        new Tema(VALID_ID_TEMA, "Andrei", 10, 12)
                )
        );
    }

    @Test
    public void testAddStudentAndAddAssignmentAndAddGrade(){
        Service service = createService();
        removeStudentWithValidId(service);
        removeTemaWithValidId(service);
        removeNotaWithValidId(service);

        assertNull(
                service.addStudent(
                        new Student(VALID_ID, "Andrei", 10, "email@email.com")
                )
        );

        assertNull(
                service.addTema(
                        new Tema(VALID_ID_TEMA, "Andrei", 10, 12)
                )
        );

        assertEquals(
                8,
                service.addNota(
                        new Nota(VALID_ID_NOTA, "BAIE2466", "6", 8, LocalDate.of(2021, 4, 13)), ""
                ),
                0.1
        );
    }
}
