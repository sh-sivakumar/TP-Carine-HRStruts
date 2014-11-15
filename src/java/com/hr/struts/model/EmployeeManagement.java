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

    @Override
    public boolean delete(String ssNum) throws ServiceIndisponibleException {
        try {
            eDao.delete(ssNum);
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
            return eDao.create(name, ssNum, phone);
        } catch (Exception ex) {

            throw new ServiceIndisponibleException("Service indisponible", ex);
        }
  
    }

    @Override
    public boolean update(String name, String ssNum, String phone) throws ServiceIndisponibleException {
        
        try {
            return eDao.update(name, ssNum, phone);
        } catch (Exception ex) {

            throw new ServiceIndisponibleException("Service indisponible", ex);
        }
        
    }

}
