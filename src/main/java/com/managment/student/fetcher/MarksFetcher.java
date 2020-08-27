package com.managment.student.fetcher;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.managment.student.interfaces.StudentService;
import com.managment.student.model.Marks;
import com.managment.student.model.Student;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarksFetcher {

    @Autowired
    StudentService studentService;


    public DataFetcher<List<Student>> findAll() {
        return dataFetchingEnvironment -> {
            return studentService.findAllStudentMarks();
        };
    }


    public DataFetcher<Marks> save() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            Marks marks =mapper.convertValue(dataFetchingEnvironment.getArgument("studentMarks"),Marks.class);
            return studentService.saveMarks(marks);
        };
    }
}
