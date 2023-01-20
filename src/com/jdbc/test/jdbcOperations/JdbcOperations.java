package com.jdbc.test.jdbcOperations;

import com.jdbc.test.empinfo.EmployeeInfo;

public interface JdbcOperations {

        void createTable();

         void select ();

         void insertTable (EmployeeInfo em);

         void updateTable (EmployeeInfo em);

         void deleteColumn (EmployeeInfo em);
}
