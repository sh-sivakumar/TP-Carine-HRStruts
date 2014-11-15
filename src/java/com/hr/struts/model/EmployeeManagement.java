package com.hr.struts.model;

import com.hr.struts.exception.ServiceIndisponibleException;
import com.hr.struts.model.dao.EmployeeDao;
import com.hr.struts.model.dao.IEmployeeDao;
import com.hr.struts.model.entities.Employee;
import com.hr.struts.plugin.Factory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeManagement extends SuperModel implements IEmployeeManagement<Employee> {

    private IEmployeeDao eDao;

    public EmployeeManagement() {
        this.eDao = (IEmployeeDao) new Factory().instantiate("com.hr.struts.model.dao.EmployeeDao"); // factory a utiliser
    }

    @Override
    public ArrayList<Employee> searchByTransfer(int type, String value) throws ServiceIndisponibleException {
        List result = null;

        switch (type) {
            //name
            case 1:
                result = eDao.findByName(value);
                break;
            //ssNum
            case 2:
                result = eDao.findBySSNum(value);
                break;
            //phone    
            case 3:
                result = eDao.findByPhone(value);
                break;
        }

        return (ArrayList) result;
    }

    /*
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
     }*/

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
            eDao.delete(get);
        } catch (Exception e) {
            throw new ServiceIndisponibleException("Delete indisponible", e);
        }
        return true;
    }

    @Override
    public ArrayList<Employee> findAll() throws ServiceIndisponibleException {

        try {
            return (ArrayList) eDao.findAll();
        } catch (Exception ex) {

            throw new ServiceIndisponibleException("Service indisponible", ex);
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
