package com.managment.student.interfaces;

import com.managment.student.model.Marks;
import com.managment.student.model.Student;

import java.util.List;

public interface StudentService {

    /**
     * it will generate rollno based on branch,name starting 3 letter,year
     * todo:add admin support or a deadline fix by admin on which it will genarate all rollno for new student
     * @return no. of rollno generated or no. of new student added
     */
    int generateRollNo();

    Student saveStudent(Student student);

    Marks saveMarks(Marks marks);

    List<Student> findAllStudent();


    List<Marks> findAllStudentMarks();


}

