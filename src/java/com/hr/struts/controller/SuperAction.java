/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.controller;

import com.hr.struts.model.IEmployeeManagement;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.actions.MappingDispatchAction;

/**
 *
 * @author Sinthu
 */
public abstract class SuperAction extends MappingDispatchAction {

    protected static final String SERVICE = "SERVICE";

    protected IEmployeeManagement getModel(HttpServletRequest request) {

        IEmployeeManagement iem = (IEmployeeManagement) request.getServletContext().getAttribute(SERVICE);
        return iem;
    }
}
