package com.managment.student.service;

import com.managment.student.interfaces.StudentService;
import com.managment.student.model.Marks;
import com.managment.student.model.Student;
import com.managment.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IStudentService implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public int generateRollNo() {
        List<Student> studentList = studentRepository.findAll();
        studentRepository.saveAll(
                studentList.stream()
                        .filter(student -> {
                            if(student.getMarks() == null) {
                                return true;
                            }
                            return false;
                        })
                        .map(this::getRollNo)
                        .collect(Collectors.toList()));
        return studentList.size();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Marks saveMarks(Marks marks) {
        Student student = studentRepository.findByMarks(marks);
        student.setMarks(marks);
        studentRepository.save(student);
        return marks;
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentMarks() {
        return studentRepository.findAll();

    }

    Student getRollNo(Student student){
        Marks marks =new Marks();
        marks.setRollNo(student.getBranch()+student.getStudentId()+student.getStudentName().substring(0,3));
        student.setMarks(marks);
        return student;
    }
}
