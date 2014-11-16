package com.hr.struts.model;

import com.hr.struts.exception.ServiceIndisponibleException;
import com.hr.struts.model.dao.IEmployeeDao;
import com.hr.struts.model.entities.Employee;
import com.hr.struts.plugin.Factory;
import java.util.ArrayList;
import java.util.List;
import com.myapp.struts.Constants;
import java.util.Properties;

public class EmployeeManagement implements IEmployeeManagement<Employee> {

    private IEmployeeDao eDao;

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
            if (eDao.ssNumExist(ssNum)) {
                return eDao.delete(ssNum);
            }
        } catch (Exception e) {
            throw new ServiceIndisponibleException("Delete indisponible", e);
        }

        return false;
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
            if (!eDao.ssNumExist(ssNum)) {
                return eDao.create(name, ssNum, phone);
            }
        } catch (Exception ex) {

            throw new ServiceIndisponibleException("Service indisponible", ex);
        }

        return false;
    }

    @Override
    public boolean update(String name, String ssNum, String phone) throws ServiceIndisponibleException {

        try {
            if (eDao.ssNumExist(ssNum)) {
                return eDao.update(name, ssNum, phone);
            }
        } catch (Exception ex) {

            throw new ServiceIndisponibleException("Service indisponible", ex);
        }

        return false;
    }

    @Override
    public void setDao(Properties properties) {
        if (this.eDao == null) {
            String daoClass = properties.getProperty(Constants.PROP_EMDAOCLASS);
            this.eDao = (IEmployeeDao) new Factory().instantiate(daoClass);
        }

    }

}
