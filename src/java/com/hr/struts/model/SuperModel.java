/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author franckmazzolo
 */
public abstract class SuperModel {

    //protected String datasource;
    //protected String driver, url, login, pwd;

    /*
     // recuperation de la connexion
     protected Connection getConnection() throws SQLException {
     try {
     //Loading driver
     System.out.println("driver" + driver);
     Class.forName(driver);
     } catch (ClassNotFoundException ex) {
     Logger.getLogger(SuperModel.class.getName()).log(Level.SEVERE, null, ex);
     }
     return DriverManager.getConnection(url, login, pwd);
     }
     */
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

    /**
     *
     * @param properties
     */
    /*
     public void setConnectionInfo(Properties properties) {
     this.driver = properties.getProperty("driver", "Sans Driver");
     this.url = properties.getProperty("url", "Sans URL");
     this.login = properties.getProperty("login", "Sans Login");
     this.pwd = properties.getProperty("pwd", "Sans Password");
     }
     */
}
