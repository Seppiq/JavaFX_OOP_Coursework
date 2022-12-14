package com.example.demo1.Service;


import com.example.demo1.Model.Student;

import java.io.File;
import java.util.List;

public interface StudentService {

 List<Student> instructionSearch(String str);

 List<Student> nameSearch(String str);

 List<Student> surnameSearch(String str);

 Student getStudentById(int id);

 List<Student> getAllStudents();

 void createStudent(Student student);

 void updateStudentById(int id, Student student);

 void deleteStudentById(int id);

 void saveFile(File saveFile);
}
