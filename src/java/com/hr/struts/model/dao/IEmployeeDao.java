/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model.dao;

import com.hr.struts.exception.DAOException;
import com.hr.struts.model.entities.Employee;
import java.util.List;

/**
 *
 * @author franckmazzolo
 */
public interface IEmployeeDao {

    public boolean create(String name, String ssNum, String phone) throws DAOException;

    public List<Employee> findByName(String name) throws DAOException;

    public List<Employee> findBySSNum(String ssNum) throws DAOException;

    public List<Employee> findByPhone(String phone) throws DAOException;

    public List<Employee> findAll() throws DAOException;

    public boolean update(String name, String ssNum, String phone) throws DAOException;

    public boolean delete(String ssNum) throws DAOException;

    public boolean ssNumExist(String ssNum) throws DAOException;
    
}
