package ssvv.example;

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
import validation.ValidationException;

import static org.junit.Assert.*;

public class AppTest 
{
    private final String VALID_ID = "baie2466",
                            VALID_ID_TEMA = "7";

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

    // addStudent tests

    @Test
    public void testAddStudentValid(){
        Service service = createService();
        removeStudentWithValidId(service);

        assertNull(
                service.addStudent(
                        new Student(VALID_ID, "Andrei", 10, "email@email.com")
                )
        );
    }

    @Test
    public void testAddStudentWithInvalidGroup(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            assertNull(
                    service.addStudent(
                            new Student(VALID_ID, "Andrei", -10, "email@email.com")
                    )
            );
            fail();
        } catch(ValidationException ignored){}
    }

    @Test
    public void testAddStudentWithIvalidIDEmpty(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student("", "Andrei", 10, "email@email.com")
            );
            fail();
        } catch (ValidationException ignored){}
    }

    @Test
    public void testAddStudentWithIvalidIDNull(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(null, "Andrei", 10, "email@email.com")
            );
            fail();
        } catch (ValidationException ignored){}
    }

    @Test
    public void testAddStudentWithInvalidNameEmpty(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(VALID_ID, "", 10, "email@email.com")
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddStudentWithInvalidNameNull(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(VALID_ID, null, 10, "email@email.com")
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddStudentWithEmailEmpty(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(VALID_ID, "Andrei", 10, "")
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddStudentWithEmailNull(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(VALID_ID, "Andrei", 10, null)
            );
            fail();
        } catch (Exception ignored){}
    }

    // BVA

    @Test
    public void testAddStudentWithInvalidGroupBva1(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            assertNull(
                    service.addStudent(
                            new Student(VALID_ID, "Andrei", -1, "baie2466@scs.ubbcluj.ro")
                    )
            );
            fail();
        } catch(ValidationException ignored){}
    }

    @Test
    public void testAddStudentWithInvalidGroupBva2(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            assertNull(
                    service.addStudent(
                            new Student(VALID_ID, "Andrei", 0, "baie2466@scs.ubbcluj.ro")
                    )
            );
        } catch(ValidationException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddStudentWithInvalidGroupBva3(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            assertNull(
                    service.addStudent(
                            new Student(VALID_ID, "Andrei", 1, "baie2466@scs.ubbcluj.ro")
                    )
            );
        } catch(ValidationException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddStudentWithEmailBva(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(VALID_ID, "Andrei", 10, "a")
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddStudentWithNameBva(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student(VALID_ID, "A", 10, "email@email.com")
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddStudentWithIdBva(){
        Service service = createService();
        removeStudentWithValidId(service);

        try{
            service.addStudent(
                    new Student("a", "Andrei", 10, "email@email.com")
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    // addTema test cases

    @Test
    public void testAddTemaWithIDInvalid(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(null, "asb", 12, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithIDEmpty(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema("", "asb", 12, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithIDInvalidBVA(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema("a", "asb", 12, 12)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithDescriptionInvalid(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "", 12, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithDescriptionNull(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, null, 12, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithDescriptionInvalidBVA(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 12)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithDeadlineNegative(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", -12, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithDeadlinePositiveInvalid(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 20, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithDeadlineInvalidBVA(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 0, 12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithDeadlineValidBVA(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 1, 12)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithDeadlineValidBVA_1(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 2, 12)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithDeadlineValidBVA_2(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 13, 12)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithDeadlineValidBVA_3(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 14, 12)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithDeadlineInvalidBVA_1(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 15, 12)
            );
            fail();
        } catch (Exception ignored){
        }
    }

    @Test
    public void testAddTemaWithPrimireNegative(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, -12)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithPrimirePositiveInvalid(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 20)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithPrimireInvalidBVA(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 0)
            );
            fail();
        } catch (Exception ignored){}
    }

    @Test
    public void testAddTemaWithPrimireValidBVA(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 1)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithPrimireValidBVA_1(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 2)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithPrimireValidBVA_2(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 13)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithPrimireValidBVA_3(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 14)
            );
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddTemaWithPrimireInvalidBVA_1(){
        Service service = createService();
        removeTemaWithValidId(service);

        try{
            service.addTema(
                    new Tema(VALID_ID_TEMA, "a", 12, 15)
            );
            fail();
        } catch (Exception ignored){
        }
    }
}
