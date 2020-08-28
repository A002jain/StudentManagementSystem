package com.managment.student.service;

import com.managment.student.exception.GraphQLErrorHandler;
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
                        .filter(student -> student.getMarks() == null && student.getYear() == 1)
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
    public List<Marks> findAllStudentMarks() {
        return studentRepository.findAll().stream()
                .map(Student::getMarks)
                .collect(Collectors.toList());

    }

    @Override
    public List<Student> findFilterStudent(int year, String branch) {
        return studentRepository.findByYearAndBranch(year,branch);
    }

    @Override
    public List<Student> findStudentByYear(int year) {
        return studentRepository.findByYear(year);
    }

    @Override
    public List<Student> findStudentByBranch(String branch) {
        return studentRepository.findByBranch(branch);
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentRepository.findByStudentName(name);
    }

    @Override
    public Student findStudentById(String id) {
        return studentRepository.findById(id).
                orElse(null);
    }

    @Override
    public Student findStudentByRollNo(String rollNo) {
//        Marks marks =new Marks();
//        marks.setRollNo(rollNo);
//        return studentRepository.findByMarks(marks).getMarks();
        return studentRepository.findStudentByRollNo(rollNo);
    }

    @Override
    public Student updateStudent(Student student) {
        return null;
    }

    @Override
    public Student  deleteStudentById(String studentId) {
        studentRepository.deleteById(studentId);
        return null;
    }

    @Override
    public Student deleteStudentByRollNo(String rollNo) {
        studentRepository.deleteByStudentRollNo(rollNo);
        return null;
    }

    Student getRollNo(Student student){
        Marks marks =new Marks();
        marks.setRollNo(student.getBranch()+student.getStudentId()+student.getStudentName().substring(0,3));
        student.setMarks(marks);
        return student;
    }
}
