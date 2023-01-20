package com.jdbc.test.emp;
import com.jdbc.test.empinfo.EmployeeInfo;
import com.jdbc.test.jdbcOperations.JdbcOperations;

import java.sql.*;
import java.util.Scanner;

public class emp implements JdbcOperations {
    Connection con;
    final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    final String usrname = "Nikhil";
    final String passwd = "nikhil";

    public emp() {
        try {
            con = DriverManager.getConnection(url, usrname, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void createTable() {
        try {
    /* Scanner sc=new Scanner(System.in);
      System.out.println("enter a query for creating a table");
      String sql=sc.next();*/
            String sql = "create table EmpDetails(empid number(10) primary key,empname varchar(20) not null,empsalary number(20) not null,designation varchar(20) not null)";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("table created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void select() {
        try {
            String sql = "select * from EmpDetails";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("List of employee details");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3) + " " + rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTable(EmployeeInfo em) {
        try {
               /*System.out.println("enter a insert query");
               String sql=sc.next();*/
            String sql = "insert into EmpDetails values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            System.out.println("enter id");
            int id = sc.nextInt();
            em.setEmpid(id);
            System.out.println("enter employee name");
            String name = sc.next();
            em.setEmpname(name);
            System.out.println("enter salary");
            double salary = sc.nextDouble();
            em.setEmpsalary(salary);
            System.out.println("enter designation of employee");
            String desig = sc.next();
            em.setDesignation(desig);
            pst.setInt(1, em.getEmpid());
            pst.setString(2, em.getEmpname());
            pst.setDouble(3, em.getEmpsalary());
            pst.setString(4, em.getDesignation());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(EmployeeInfo em) {
        try {
         /* System.out.println("enter a salary update query");
          String sql=sc.next();*/
            String sql = "update EmpDetails set empsalary=? where empid=?";
            System.out.println("enter salary you want to update");
            double salary = sc.nextDouble();
            em.setEmpsalary(salary);
            System.out.println("enter empid for salary update");
            int id = sc.nextInt();
            em.setEmpid(id);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, em.getEmpsalary());
            ps.setInt(2, em.getEmpid());
            ps.executeUpdate();
            System.out.println("updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteColumn(EmployeeInfo em) {
        try {
          /* System.out.println("enter delete query based on empid");
           String sql=sc.next();*/
            String sql = "delete from EmpDetails where Empid=?";
            System.out.println("enter empid you want to delete");
            int id = sc.nextInt();
            em.setEmpid(id);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, em.getEmpid());
            ps.executeUpdate();
            System.out.println("executed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
