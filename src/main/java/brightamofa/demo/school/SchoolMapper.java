package brightamofa.demo.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toschoolDto(School school){
        return new SchoolDto(school.getName());
    }
}
