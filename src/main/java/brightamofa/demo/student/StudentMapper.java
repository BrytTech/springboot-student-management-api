package brightamofa.demo.student;

import brightamofa.demo.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto){

        if ((dto == null)){
            throw new NullPointerException("The student Dto should not be null");
        }

        var student = new Student();
        student.setFirstName(dto.firstname());
        student.setLastName(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto tostudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );

    }
}
