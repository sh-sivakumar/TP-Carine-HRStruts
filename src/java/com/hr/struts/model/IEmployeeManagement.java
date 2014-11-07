/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author franckmazzolo
 * @param <Entity>
 */
public interface IEmployeeManagement<Entity> {

    public boolean add(String name, String ssNum, String phone);

    public boolean delete(Entity get);

    public boolean update(Entity e, String name, String ssNum, String phone);

    public ArrayList<Entity> searchBy(String requete) throws Exception;

    public ArrayList<Entity> searchByTransfer(int type, String value) throws Exception;

    public ArrayList<Entity> findAll() throws Exception;

    public void setConnectionInfo(Properties properties);
}
