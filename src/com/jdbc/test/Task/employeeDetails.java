package com.jdbc.test.Task;
import com.jdbc.test.emp.*;
import com.jdbc.test.empinfo.*;

import java.lang.*;
import java.util.*;

public class employeeDetails{
    public static void main(String[] args) throws Exception{
       EmployeeInfo ed= new EmployeeInfo();
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