package brightamofa.demo.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto dto){
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.tostudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findaAllStudent(){
        return repository.findAll()
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public StudentResponseDto findStudentById( Integer id) {
        return repository.findById(id)
                .map(studentMapper::tostudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findStudentByName(String name) {
        return repository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }


}
