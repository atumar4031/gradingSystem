package com.grading.dao;
import com.grading.model.Grade;
import com.grading.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class dao implements istudent, igrade{
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;
    private Student student;
    private Grade grade;
    Scanner scan = new Scanner(System.in);
    // constructor
    public dao(){
        this.url = "jdbc:mysql://localhost:3306/grading?useSSH=false";
        this.username = "root";
        this.password = "root";
        connection =  getSQLConnection();

    }
    @Override
    public Connection getSQLConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception ex){
            ex.printStackTrace();

        }
        return connection;
    } // done and connected

    // implementing STUDENT Interface
    @Override
    public boolean create(Object o) {
        boolean flag = false;
        String values;
        String packageName = o.getClass().getName();
        int delimiterIndex = packageName.lastIndexOf('.');
        String className = packageName.substring(delimiterIndex + 1);
        // end
        String table = className.toLowerCase();
        switch (className){
            case "Student" -> {
                 values = " VALUES (?,?,?,?,?)";
                student = (Student) o;

                try{
                    String insertSQL = "INSERT INTO "+ table +""+values;
                    PreparedStatement ps = connection.prepareStatement(insertSQL);
                    ps.setLong(1, student.getId());
                    ps.setString(2, student.getName());
                    ps.setString(3, student.getStudent_number());
                    ps.setString(4, student.getEmail());
                    ps.setString(5, student.getPhone());
                    int affected = ps.executeUpdate();
                    if (affected > 0)
                        flag = true;
                }catch(Exception e){
                    e.printStackTrace();
                 }
            }
            case "Grade" -> {
                values = " VALUES (?,?,?,?)";
                grade = (Grade) o;

                try{
                    String query = "INSERT INTO "+ table + ""+ values;
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setLong(1, grade.getId());
                    ps.setLong(2, grade.getCa_score());
                    ps.setLong(3, grade.getExam_score());
                    ps.setLong(4, grade.getStudent_id());
                    int affected = ps.executeUpdate();
                    if (affected > 0)
                        flag = true;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    } // done

    @Override
    public boolean updateStudent(long id) {
        boolean status = false;
           int choice = op1ORop2("name", "email");
            String option;
            String name = null;
            String email = null;
            if(choice == 1){
                option = "name";
                System.out.println("update name: ");
                name = scan.nextLine();
            }else{
                option = "email";
                System.out.println("update email: ");
                email = scan.nextLine();
            }

        try{
            String updateSQL = "UPDATE student SET "+ option +" = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, option == "name" ? name : email);
            preparedStatement.setString(2, String.valueOf(id));
            int affected = preparedStatement.executeUpdate();
            if(affected > 0)
                status = true;

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return status;
    } // done


    @Override
    public ArrayList<Student> findAllStudent() {
        ArrayList<Student> students = new ArrayList<>();

        try{
            String selectSQL = "SELECT * FROM student";
            Statement stm = connection.createStatement();
            ResultSet resultset = stm.executeQuery(selectSQL);

            while(resultset.next()){
                student = new Student();
                student.setId(resultset.getInt("id"));
                student.setName(resultset.getString("name"));
                student.setStudent_number(resultset.getString("student_number"));
                student.setEmail(resultset.getString("email"));
                student.setPhone(resultset.getString("phone"));
                students.add(student);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return students;
    } // done

    @Override
    public boolean deleteStudent(long id) {
        boolean deleteStatus = false;
        try{
            String deleteSQL = "DELETE student FROM student WHERE id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(
            1, (int) id);
            int affected = preparedStatement.executeUpdate();
            if(affected > 0)
                deleteStatus = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return deleteStatus;
    } // done
// implementing GRADE Interface
    @Override
    public ArrayList<Grade> findGrade() {
        ArrayList<Grade> grades = new ArrayList<>();
        String selectGradeSQL = "SELECT * FROM grade";
        try{
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(selectGradeSQL);
            while(resultSet.next()){
                grade = new Grade();
                grade.setId(resultSet.getInt("id"));
                grade.setCa_score(resultSet.getInt("ca_score"));
                grade.setExam_score(resultSet.getInt("exam_score"));
                grade.setStudent_id(resultSet.getInt("student_id"));
                grades.add(grade);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return grades;
    } // done

    @Override
    public boolean updateGrade(long id) {
        int ca = 0;
        int exam = 0;
        int choice = op1ORop2("ca_score", "exam_score");
        ArrayList<Student> students = findAllStudent();
        boolean updateStatus = false;
        String option = null;
        Student student =  students.stream()
                                .filter(st -> st.getId() == id)
                                .findFirst()
                                .orElse(null);
        int student_id = (int) student.getId();
        System.out.println("Enter course code: ");
        String courseCode = scan.nextLine();
        if(choice == 1){
            option = "ca_score";
            System.out.println("update ca: ");
            ca = Integer.parseInt(scan.nextLine());
        }else{
            option = "exam_score";
            System.out.println("update Exam score: ");
            exam = Integer.parseInt(scan.nextLine());
        }
        String updateGradeSQL = "UPDATE grade SET "+ option +"= ? WHERE course_code = ? and student_id = ? ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(updateGradeSQL);
            preparedStatement.setInt(1, option == "ca_score" ? ca : exam);
            preparedStatement.setString(2, courseCode);
            preparedStatement.setInt(3, (int) student_id);
            int affected = preparedStatement.executeUpdate();
            if(affected > 0)
                updateStatus = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return updateStatus;
    } // done

    @Override
    public boolean deleteGrade(long id) {
        return false;
    }

// helper functions
private int op1ORop2(String op1, String op2) {
    System.out.println("1." + op1);
    System.out.println("2." + op2);
    return Integer.parseInt(scan.nextLine());
}
}