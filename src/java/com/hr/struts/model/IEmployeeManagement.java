/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import com.hr.struts.exception.ServiceIndisponibleException;
import java.util.ArrayList;

/**
 *
 * @author franckmazzolo
 * @param <Entity>
 */
public interface IEmployeeManagement<Entity> {

    public boolean add(String name, String ssNum, String phone) throws ServiceIndisponibleException;

    public boolean delete(String ssNum) throws ServiceIndisponibleException;

    public boolean update(String name, String ssNum, String phone) throws ServiceIndisponibleException;

    public ArrayList<Entity> searchByTransfer(int type, String value) throws ServiceIndisponibleException;

    public ArrayList<Entity> findAll() throws ServiceIndisponibleException;

    //public void setConnectionInfo(Properties properties);
}
