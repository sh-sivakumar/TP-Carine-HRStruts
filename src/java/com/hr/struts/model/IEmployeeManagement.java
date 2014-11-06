/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import com.hr.struts.model.entities.Employee;

/**
 *
 * @author franckmazzolo
 */
public interface IEmployeeManagement {
    public boolean add(Employee get);
    public boolean delete(Employee get);
    public boolean update(Employee get);
}
