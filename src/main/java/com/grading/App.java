package com.grading;

import com.grading.dao.dao;
import com.grading.model.Student;
import com.grading.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final StudentService studentService = new StudentService();
    private static ArrayList<Student> students;
    public static void main(String[] args) {
        /*create student
            boolean createFlag = studentService.createStudentService();
            System.out.println(createFlag ? "student inserted" : "failed to insert");
         */

        /* show all student
            students = studentService.getStudentsService();
            students.stream().forEach(System.out::println);
         */

        /* update student record
           boolean updateFlag = studentService.updateStudentService();
           System.out.println(updateFlag ? "student updated" : "failed to update");
         */


        //       System.out.println(d.updateGrade(102));


//        d.updateStudent(101); // update student
       /*        Grade grade = new Grade();
///        grade.setId(1);
///        grade.setCa_score(40);
///        grade.setExam_score(60);
//       grade.setStudent_id(101);
//       var createFlag = d.create(grade);
//
//        System.out.println(createFlag ? "Grade inserted" : "failed to insert");
//
       d.create(new Grade());
 */

    }
}
