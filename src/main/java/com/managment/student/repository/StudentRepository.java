package com.managment.student.repository;

import com.managment.student.model.Marks;
import com.managment.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByMarks(Marks marks);

}
