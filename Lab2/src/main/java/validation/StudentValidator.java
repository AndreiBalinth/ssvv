package validation;

import domain.Student;

public class StudentValidator implements validation.Validator<Student> {

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws validation.ValidationException {
        if(entity.getID().equals("")){
            throw new validation.ValidationException("Id incorect!");
        }
        if(entity.getID() == null){
            throw new validation.ValidationException("Id incorect!");
        }
        if(entity.getNume() == ""){
            throw new validation.ValidationException("Nume incorect!");
        }
        if(entity.getGrupa() < 0) {
            throw new validation.ValidationException("Grupa incorecta!");
        }
        if(entity.getEmail() == null){
            throw new validation.ValidationException("Email incorect!");
        }
        if(entity.getNume() == null){
            throw new validation.ValidationException("Nume incorect!");
        }
        if(entity.getEmail().equals("")){
            throw new validation.ValidationException("Email incorect!");
        }
    }
}
