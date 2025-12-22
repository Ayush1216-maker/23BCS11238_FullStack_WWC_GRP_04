package com.example.StudentRegistration.service;
import com.example.StudentRegistration.model.studentModel;
import java.util.ArrayList;
import java.util.List;
public class studentService {
  private List<studentModel>s1=new ArrayList<>();

    public List<studentModel> getallstudent() {
        return s1;
    }
    public studentModel getstudentById(int id){
        return s1.stream()
                .filter(e->e.getId()==id)
                .findFirst().orElse(null);
    }
    public studentModel addStudent(studentModel s){
        s1.add(s) ;
        return s;
    }
    public void registerStudent(studentModel s) {

        boolean exists = s1.stream()
                .anyMatch(e -> e.getId() == s.getId());

        if (exists) {
            throw new IllegalArgumentException("Student ID already exists");
        }

        s1.add(s);
    }
    public boolean deleteStudent(int id){
        return s1.removeIf(e->e.getId()==id);
    }

}
