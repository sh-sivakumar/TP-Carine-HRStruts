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

    public void create(Employee e) throws DAOException;

    public List<Employee> findByName(String name) throws DAOException;

    public List<Employee> findBySSNum(String ssNum) throws DAOException;

    public List<Employee> findByPhone(String phone) throws DAOException;

    public List<Employee> findAll() throws DAOException;

    public void update() throws DAOException;

    public void delete(Employee e) throws DAOException;
}
