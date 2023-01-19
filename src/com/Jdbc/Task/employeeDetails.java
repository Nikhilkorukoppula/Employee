package com.Jdbc.Task;
import java.lang.*;
import java.sql.*;
import java.util.*;

interface JdbcOperations{
    void createTable();
    void select();
    void insertTable(EmployeeInfo em);
    void updateTable(EmployeeInfo em);
    void deleteColumn(EmployeeInfo em);
}
class EmployeeInfo{
    private int empid;
    private String empname;
    private double empsalary;
    private String designation;
    EmployeeInfo(int empid,String empname,double empsalary,String designation){
        this.empid=empid;
        this.empname=empname;
        this.empsalary=empsalary;
        this.designation=designation;
    }
    public void setEmpid(int empid){
        this.empid=empid;
    }
    public int getEmpid(){
        return empid;
    }
    public void setEmpname(String empname){
        this.empname=empname;
    }
    public String getEmpname(){
        return empname;
    }
   public void setEmpsalary(double empsalary){
        this.empsalary=empsalary;
    }
    public double getEmpsalary(){
        return empsalary;
    }
    public void setDesignation(String designation){
        this.designation=designation;
    }
    public String getDesignation(){
        return designation;
    }
}
 class emp implements JdbcOperations {
     Connection con;
    final String url="jdbc:oracle:thin:@localhost:1521:xe";
    final  String usrname="Nikhil";
    final String passwd="nikhil";
    public emp(){
      try{
          con= DriverManager.getConnection(url,usrname,passwd);
      }
      catch(Exception e){
          e.printStackTrace();
      }
    }
     Scanner sc=new Scanner(System.in);
    @Override
    public void createTable() {
   try{
     /* Scanner sc=new Scanner(System.in);
       System.out.println("enter a query for creating a table");
       String sql=sc.next();*/
      String sql="create table EmpDetails(empid number(10) primary key,empname varchar(20) not null,empsalary number(20) not null,designation varchar(20) not null)";
       Statement st=con.createStatement();
       int rs=st.executeUpdate(sql);
       System.out.println("table created");
       con.close();
   }
   catch(Exception e){
       e.printStackTrace();
   }
    }

     @Override
     public void select() {
         try{
             String sql="select * from EmpDetails";
             PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
             System.out.println("List of employee details");
             while(rs.next()){
                 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getString(42));
             }
             con.close();
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }

     @Override
    public void insertTable(EmployeeInfo em) {
            try{
                /*System.out.println("enter a insert query");
                String sql=sc.next();*/
                String sql="insert into EmpDetails values(?,?,?,?)";
                PreparedStatement pst=con.prepareStatement(sql);
                System.out.println("enter id");
                int id=sc.nextInt();
                em.setEmpid(id);
                System.out.println("enter employee name");
                String name=sc.next();
                em.setEmpname(name);
                System.out.println("enter salary");
                double salary=sc.nextDouble();
                em.setEmpsalary(salary);
                System.out.println("enter designation of employee");
                String desig=sc.next();
                em.setDesignation(desig);
                pst.setInt(1,em.getEmpid());
                pst.setString(2,em.getEmpname());
                pst.setDouble(3,em.getEmpsalary());
                pst.setString(4,em.getDesignation());
                pst.executeUpdate();
                con.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }

    @Override
    public void updateTable(EmployeeInfo em)  {
       try{
          /* System.out.println("enter a salary update query");
           String sql=sc.next();*/
           String sql="update EmpDetails set empsalary=? where empid=?";
           System.out.println("enter salary you want to update");
           double salary=sc.nextDouble();
           em.setEmpsalary(salary);
           System.out.println("enter empid for salary update");
           int id=sc.nextInt();
           em.setEmpid(id);
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setDouble(1,em.getEmpsalary());
           ps.setInt(2,em.getEmpid());
           ps.executeUpdate();
           System.out.println("updated");
           con.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public void deleteColumn(EmployeeInfo em) {
        try{
           /* System.out.println("enter delete query based on empid");
            String sql=sc.next();*/
            String sql="delete from EmpDetails where Empid=?";
            System.out.println("enter empid you want to delete");
            int id=sc.nextInt();
            em.setEmpid(id);
            PreparedStatement ps=con.prepareStatement(sql);
           ps.setInt(1,em.getEmpid());
            ps.executeUpdate();
            System.out.println("executed");
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}

public class employeeDetails{
    public static void main(String[] args) throws Exception{
       EmployeeInfo ed= new EmployeeInfo(1,"nikhil",10000.0,"trainee");
        emp e=new emp();
        Scanner sc = new Scanner(System.in);
        int a;
       do{
           System.out.println("enter your option:");
           System.out.println("1.create, 2.select, 3.insert, 4.update, 5.delete, 6.Exit");
           a=sc.nextInt();
           switch(a){
               case 1:
                   e.createTable();
                   break;
               case 2:
                   e.select(); break;
               case 3:
                   e.insertTable(ed); break;
               case 4:
                   e.updateTable(ed);break;
               case 5:
                   e.deleteColumn(ed);break;
               case 6:
                   System.out.println("exited");
               default:break;
           }
       }
       while(a!=6);
    }
}