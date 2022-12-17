package com.example.demo1.Repository;


import com.example.demo1.Model.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> instructionSearch(String str);

    List<Student> nameSearch(String str);

    List<Student> surnameSearch(String str);

    List<Student> getAllStudent() ;

    void deleteStudentById(int id);

    void updateStudentById(int id, Student student);

    void saveStudent(Student student);

    Student getStudentById(int id);

}
