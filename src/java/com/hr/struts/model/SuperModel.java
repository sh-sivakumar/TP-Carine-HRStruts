/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franckmazzolo
 */
public abstract class SuperModel {

    protected String driver, url, login, pwd;

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

    /**
     * 
     * @param properties 
     */
    public void setConnectionInfo(Properties properties) {
        this.driver = properties.getProperty("driver", "Sans Driver");
        this.url = properties.getProperty("url", "Sans URL");
        this.login = properties.getProperty("login", "Sans Login");
        this.pwd = properties.getProperty("pwd", "Sans Password");
    }
}
