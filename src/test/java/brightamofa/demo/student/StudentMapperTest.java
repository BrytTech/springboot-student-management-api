package brightamofa.demo.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        //Arrange > prepare the data
        StudentDto dto = new StudentDto(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                1
        );

        //Act > call the method being tested
        Student student = mapper.toStudent(dto);

        //Assert > check if results is correct
        assertEquals(dto.firstname(), student.getFirstName());
        assertEquals(dto.lastname(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
        var exp = assertThrows(NullPointerException.class, ()-> mapper.toStudent(null));
        assertEquals("The student Dto should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //Given
        Student student = new Student(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                23
        );

        //When
        StudentResponseDto response = mapper.tostudentResponseDto(student);

        //Then
        assertEquals(response.firstname(), student.getFirstName());
        assertEquals(response.lastname(), student.getLastName());
        assertEquals(response.email(), student.getEmail());
    }

}