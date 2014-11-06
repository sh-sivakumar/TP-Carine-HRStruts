package com.hr.struts.model;

import com.hr.struts.controller.SuperAction;
import com.hr.struts.model.entities.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class EmployeeManagement {

    private static EmployeeManagement service;

    //Singleton pattern
    public static EmployeeManagement getInstance() {
        if (null == service) { // Premier appel
            service = new EmployeeManagement();
        }
        return service;
    }

    private EmployeeManagement() {

    }

    /*
    // Search for employees by name.
    public ArrayList<Employee> searchByName(String name, Connection conn) throws SQLException {
        ArrayList resultList = new ArrayList();
        for (Employee employee : employees) {
            if (employee.getName().toUpperCase().contains(name.toUpperCase())) {
                resultList.add(employee);
            }
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        Employee employe = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name, ssNum, phone from employes");

            while (rs.next()) {
                employe = new Employee(rs.getString("name"),
                        rs.getString("ssNum"), rs.getString("phone"));
                arrayList.add(employe);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conn.close();
        return arrayList;
    }

    // Search for employee by social security number. 
    public ArrayList<Employee> searchBySsNum(String ssNum, Connection conn) throws SQLException {
        ArrayList resultList = new ArrayList();
        for (Employee employee : employees) {
            if (employee.getSsNum().equals(ssNum)) {
                resultList.add(employee);
            }
        }
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        Employee employe = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name, ssNum, phone from employes");

            while (rs.next()) {
                employe = new Employee(rs.getString("name"),
                        rs.getString("ssNum"), rs.getString("phone"));
                arrayList.add(employe);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conn.close();
        return arrayList;
    }
*/
    // Search for employee by phone.
    public ArrayList<Employee> searchByPhone(String phone, Connection conn) throws SQLException {
        
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        Employee employe = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name, ssNum, phone from employes "
                    + "where phone like '%" + phone + "%'");

            while (rs.next()) {
                employe = new Employee(rs.getString("name"),
                        rs.getString("ssNum"), rs.getString("phone"));
                arrayList.add(employe);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conn.close();
        return arrayList;
    }
    

    public boolean delete(Employee get) {
        //a faire
        return true;
    }

    public ArrayList<Employee> findAll (Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        Employee employe = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name, ssNum, phone from employes");

            while (rs.next()) {
                employe = new Employee(rs.getString("name"),
                        rs.getString("ssNum"), rs.getString("phone"));
                arrayList.add(employe);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conn.close();
        return arrayList;
    }

    public boolean add(String name, String ssNum, String phone) {
        //a faire
        return true;
    }

    public boolean update(Employee e, String name, String ssNum, String phone) {
        //a faire
        return true;
    }

}
