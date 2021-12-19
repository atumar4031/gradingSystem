package com.grading.service;

import com.grading.dao.dao;
import com.grading.model.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService implements  iStudentService{
    private Student student = null;
    private dao d = new dao();
    private ArrayList<Student> students;
    private Scanner scan = new Scanner(System.in);

    @Override
    public boolean createStudentService(){
        Student student = getStudentInfo();
        return d.create(student);
    } // C

    @Override
    public ArrayList<Student> getStudentsService(){
        return d.findAllStudent();
    } // R


    @Override
    public boolean updateStudentService(){
        System.out.println("Student id");
        return d.updateStudent(Integer.parseInt(scan.nextLine()));
    } // U

    @Override
    public boolean deleteStudentService(){
        return false;
    }

    // helper functions
    @Override
    public Student getStudentInfo(){
        student = new Student();

        System.out.println("Enter Student id: ");
        int id = Integer.parseInt(scan.nextLine());
        student.setId(id);

        System.out.println("Enter Student name: ");
        String name = scan.nextLine();
        student.setName(name);

        System.out.println("Enter Student email: ");
        String email = scan.nextLine();
        student.setEmail(email);

        System.out.println("Enter phone.: ");
        String phone = scan.nextLine();
        student.setPhone(phone);

        System.out.println("Enter Student no.: ");
        String student_no = scan.nextLine();
        student.setStudent_number(student_no);

        return student;
    }
}
