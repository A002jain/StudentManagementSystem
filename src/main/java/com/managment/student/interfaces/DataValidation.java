package com.managment.student.interfaces;

import com.managment.student.model.Marks;
import com.managment.student.model.Student;

import java.util.LinkedHashMap;

public interface DataValidation {

    public void validateStudent(Student student);

    public void validateMarks(Marks student);

    public void validateYear(int year);

    public void validateBranch(String branch);

    public void validateYearNBranch(int year, String branch);



}
