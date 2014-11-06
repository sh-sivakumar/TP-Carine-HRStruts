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

/**
 *
 * @author franckmazzolo
 */
public class SuperModel {

    String driver, url, login, pwd;

    // recuperation de la connexion
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        //Loading driver
        Class.forName(driver);

        return DriverManager.getConnection(url, login, pwd);
    }

    public void setConnectionInfo(Properties properties) {
        this.driver = properties.getProperty("driver", "Sans Driver");
        this.url = properties.getProperty("url", "Sans URL");
        this.login = properties.getProperty("login", "Sans Login");
        this.pwd = properties.getProperty("pwd", "Sans Password");
    }
}
