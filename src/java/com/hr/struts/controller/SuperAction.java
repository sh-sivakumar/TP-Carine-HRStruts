/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.controller;

import static com.hr.struts.controller.EmployeesShowAction.PROPERTIES;
import org.apache.struts.action.Action;
import com.hr.struts.model.EmployeeManagement;
import java.sql.Connection;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Sinthu
 */
public class SuperAction extends Action {

    protected static final String PROPERTIES = "PROPERTIES";
    protected EmployeeManagement service = EmployeeManagement.getInstance();

    public void readProperties(ServletContext context) {
        Properties properties = (Properties) context.getAttribute(PROPERTIES);
        service.setConnectionInfo(properties);
    }

}
