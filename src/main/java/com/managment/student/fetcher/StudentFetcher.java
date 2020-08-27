package com.managment.student.fetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.managment.student.interfaces.StudentService;
import com.managment.student.model.Student;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentFetcher {

    @Autowired
    StudentService studentService;

    public DataFetcher<Integer> generateRollNo(){
        return dataFetchingEnvironment -> studentService.generateRollNo();
    }

    public DataFetcher<Student> save() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            Student student=mapper.convertValue(dataFetchingEnvironment.getArgument("studentData"),Student.class);
            return studentService.saveStudent(student);
        };
    }

    public DataFetcher<List<Student>> findAll() {
        return dataFetchingEnvironment -> studentService.findAllStudent();
    }
}
