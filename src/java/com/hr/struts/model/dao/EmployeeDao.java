/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model.dao;

import com.hr.struts.exception.DAOException;
import com.hr.struts.model.entities.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author franckmazzolo
 */
public class EmployeeDao implements IEmployeeDao {

    protected Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/myDatasource");
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Search for employees by something.
    public List<Employee> searchBy(String requete) throws DAOException {
        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            Employee employe = null;

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
            throw new DAOException("Recherche non effectué !", ex);
        }

        return arrayList;
    }

    @Override
    public boolean create(String name, String ssNum, String phone) throws DAOException {
        try {
            Connection conn = null;
            Statement stmt = null;

            try {
                conn = getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO employes(name,ssnum,phone) "
                        + "VALUES ('" + name + "','" + ssNum + "','" + phone + "')");
            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException("Add non effectué !", ex);
        }

        return true;
    }

    @Override
    public boolean update(String name, String ssNum, String phone) throws DAOException {
        try {
            Connection conn = null;
            Statement stmt = null;

            try {
                conn = getConnection();
                stmt = conn.createStatement();

                stmt.executeUpdate("UPDATE employes SET name='" + name
                        + "', phone='" + phone + "' WHERE ssnum='" + ssNum + "'");

            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException("Update non effectué !", ex);
        }
        return true;
    }

    @Override
    public boolean delete(String ssNum) throws DAOException {
        try {

            Connection conn = null;
            Statement stmt = null;

            try {
                conn = getConnection();
                stmt = conn.createStatement();

                stmt.executeUpdate("DELETE FROM employes WHERE ssnum='" + ssNum + "'");
            } finally {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException("Delete non effectué !", ex);
        }

        return true;
    }

    @Override
    public List<Employee> findByName(String name) throws DAOException {
        String requete = "select name, ssNum, phone from employes "
                + "where name like '%" + name + "%'";
        return searchBy(requete);
    }

    @Override
    public List<Employee> findBySSNum(String ssNum) throws DAOException {
        String requete = "select name, ssNum, phone from employes "
                + "where ssNum = '" + ssNum + "'";
        return searchBy(requete);
    }

    @Override
    public List<Employee> findByPhone(String phone) throws DAOException {
        String requete = "select name, ssNum, phone from employes "
                + "where phone = '" + phone + "'";
        return searchBy(requete);
    }

    @Override
    public List<Employee> findAll() throws DAOException {
        try {
            Connection conn = null;
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
        } catch (Exception ex) {
            throw new DAOException(ex);
        }

    }

}
