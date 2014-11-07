package com.hr.struts.model;

import com.hr.struts.exception.ServiceIndisponibleException;
import com.hr.struts.model.entities.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManagement extends SuperModel implements IEmployeeManagement<Employee> {

    @Override
    public ArrayList<Employee> searchByTransfer(int type, String value) throws ServiceIndisponibleException {
        String requete = "";

        switch (type) {
            //name
            case 1:
                requete = "select name, ssNum, phone from employes "
                        + "where name like '%" + value + "%'";
                break;
            //ssNum
            case 2:
                requete = "select name, ssNum, phone from employes "
                        + "where ssNum = '" + value + "'";
                break;
            //phone    
            case 3:
                requete = "select name, ssNum, phone from employes "
                        + "where phone = '" + value + "'";
                break;
        }

        return searchBy(requete);
    }

    // Search for employees by something.
    public ArrayList<Employee> searchBy(String requete) throws ServiceIndisponibleException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        Employee employe = null;

        try {
            try {
                conn = getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(requete);

                while (rs.next()) {
                    employe = new Employee(rs.getString("name"),
                            rs.getString("ssNum"), rs.getString("phone"));
                    arrayList.add(employe);
                }
            } finally {
                conn.close();
            }

        } catch (SQLException ex) {
            throw new ServiceIndisponibleException(ex);
        }

        return arrayList;
    }

    /*
     // Search for employees by name.
     public ArrayList<Employee> searchByName(String name) throws SQLException {
     Connection conn = getConnection();
     Statement stmt = null;
     ResultSet rs = null;
     ArrayList<Employee> arrayList = new ArrayList<Employee>();
     Employee employe = null;
        
     try {
     stmt = conn.createStatement();
     rs = stmt.executeQuery("select name, ssNum, phone from employes "
     + "where name like '%"+name+"%'");

     while (rs.next()) {
     employe = new Employee(rs.getString("name"),
     rs.getString("ssNum"), rs.getString("phone"));
     arrayList.add(employe);
     }

     } catch (SQLException ex) {
     Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     return arrayList;
     }

     // Search for employee by social security number. 
     public ArrayList<Employee> searchBySsNum(String ssNum) throws SQLException {
     Connection conn = getConnection();
     Statement stmt = null;
     ResultSet rs = null;
     ArrayList<Employee> arrayList = new ArrayList<Employee>();
     Employee employe = null;
        
     try {
     stmt = conn.createStatement();
     rs = stmt.executeQuery("select name, ssNum, phone from employes "
     + "where ssNum = '" + ssNum + "'");

     while (rs.next()) {
     employe = new Employee(rs.getString("name"),
     rs.getString("ssNum"), rs.getString("phone"));
     arrayList.add(employe);
     }

     } catch (SQLException ex) {
     Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     return arrayList;
     }

     // Search for employee by phone.
     public ArrayList<Employee> searchByPhone(String phone) throws SQLException {
     Connection conn = getConnection();
     Statement stmt = null;
     ResultSet rs = null;
     ArrayList<Employee> arrayList = new ArrayList<Employee>();
     Employee employe = null;
        
     try {
     System.err.println(phone);
     stmt = conn.createStatement();
     rs = stmt.executeQuery("select name, ssNum, phone from employes "
     + "where phone = '" + phone + "'");


     while (rs.next()) {
     employe = new Employee(rs.getString("name"),
     rs.getString("ssNum"), rs.getString("phone"));
     arrayList.add(employe);
     }

     } catch (SQLException ex) {
     Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     return arrayList;
     }
     */
    @Override
    public boolean delete(Employee get) throws ServiceIndisponibleException {
        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection();
                stmt = conn.createStatement();
                String ssNum = get.getSsNum();
                rs = stmt.executeQuery("DELETE FROM employes WHERE ssnum=ssNum");
            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new ServiceIndisponibleException(ex);
        }
        return true;
    }

    @Override
    public ArrayList<Employee> findAll() throws ServiceIndisponibleException {
        Connection conn = null;
        try {
            try {
                conn = getConnection();
                Statement stmt = null;
                ResultSet rs = null;
                ArrayList<Employee> arrayList = new ArrayList<Employee>();
                Employee employe = null;

                stmt = conn.createStatement();
                rs = stmt.executeQuery("select name, ssNum, phone from employes");

                while (rs.next()) {
                    employe = new Employee(rs.getString("name"),
                            rs.getString("ssNum"), rs.getString("phone"));
                    arrayList.add(employe);
                }
                return arrayList;
            } finally {
                conn.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceIndisponibleException(ex);
        }

    }

    @Override
    public boolean add(String name, String ssNum, String phone) throws ServiceIndisponibleException {

        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery("insert into employes VALUES (name, ssNum, phone)");
            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new ServiceIndisponibleException(ex);
        }

        return true;
    }

    @Override
    public boolean update(Employee e, String name, String ssNum, String phone) throws ServiceIndisponibleException {
        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection();
                stmt = conn.createStatement();

                rs = stmt.executeQuery("UPDATE employes SET name=" + name + ", ssnum=" + ssNum + ", phone=" + phone + " WHERE name=" + e.getName());
            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new ServiceIndisponibleException(ex);
        }

        return true;
    }

}
