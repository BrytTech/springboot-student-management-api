package brightamofa.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    //which service we want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies
    @Mock
    StudentRepository repository;

    @Mock
    StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student(){
        //Given
        StudentDto dto = new StudentDto(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                1
        );
        Student student = new Student(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                 23
        );
        Student savedStudent = new Student(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                23
        );
        savedStudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(dto))
                .thenReturn(student);
        when(repository.save(student))
                .thenReturn((savedStudent));
        when(studentMapper.tostudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto("Bright", "Mintah", "mintahb642@gmail.com"));

        //When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        //Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository,times(1)).save(student);
        verify(studentMapper, times(1)).tostudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students(){
        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                23
        ));

        // mock the call
        when(repository.findAll()).thenReturn((students));
        when(studentMapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Bright", "Mintah", "mintahb642@gmail.com"));

        //when
        List<StudentResponseDto> responseDtos = studentService.findaAllStudent();

        //Then
        assertEquals(students.size(), responseDtos.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id(){
        //Given
        Integer studentId = 1;
        Student student = new Student(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                20
        );

        //mock the calls
        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Bright", "Mintah", "mintahb642@gmail.com"));

        //when
        StudentResponseDto dto = studentService.findStudentById(studentId);

        //Then
        assertEquals(dto.firstname(), student.getFirstName());
        assertEquals(dto.lastname(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);
    }

    @Test
    public void should_find_student_by_name(){
        //Given
        String studentName = "Bright";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Bright",
                "Mintah",
                "mintahb642@gmail.com",
                23
        ));

        // mock the call
        when(repository.findAllByFirstNameContaining(studentName)).thenReturn((students));
        when(studentMapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Bright", "Mintah", "mintahb642@gmail.com"));

        //when
        var responseDto = studentService.findStudentByName(studentName);

        //then
        assertEquals(students.size(), responseDto.size());

        verify(repository, times(1))
                .findAllByFirstNameContaining(studentName);
    }
}