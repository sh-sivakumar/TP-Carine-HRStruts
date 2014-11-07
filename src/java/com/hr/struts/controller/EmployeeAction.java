/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.controller;

import static com.hr.struts.controller.SuperAction.SERVICE;
import com.hr.struts.model.IEmployeeManagement;
import com.hr.struts.view.EmployeeSearchForm;
import com.hr.struts.view.EmployeesShowForm;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.DynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

/**
 *
 * @author Sinthu
 */
public class EmployeeAction extends SuperAction {
    
    public ActionForward addEmployee(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        
        IEmployeeManagement service = (IEmployeeManagement) request.getServletContext().getAttribute(SERVICE);
        
        boolean results;
        DynaBean addEmp = (DynaActionForm) form;
        
        String name, ssNum, phone;
        name = (String) request.getParameter("name");
        ssNum = (String) request.getParameter("ssNum");
        phone = (String) request.getParameter("phone");
        
        results = service.add(name, ssNum, phone);

        // Cible par defaut
        String cible = new String("succes");

        // Cible en cas d'echec
        if (results == false) {
            cible = new String("echec");
            ActionMessages errors = new ActionMessages();
            errors.add(null, new ActionMessage("error.add.employees.false"));
            // Signalement des erreurs a la page d'origine
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            }
        } else {
            addEmp.set("results", results);
        }
        // Transmission a la vue appropriee
        return (mapping.findForward(cible));
    }
    
    public ActionForward showEmployees(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        
        IEmployeeManagement service = (IEmployeeManagement) request.getServletContext().getAttribute(SERVICE);
        
        ArrayList results;
        //this.service.setEmployees(getEmployees(connexion(request, response)));

        EmployeesShowForm showForm = (EmployeesShowForm) form;

        // Perform the show all the employees function.
        results = service.findAll();

        // Cible par defaut
        String cible = new String("succes");

        // Cible en cas d'echec
        if (results == null) {
            cible = new String("echec");
            ActionMessages errors = new ActionMessages();
            errors.add(null, new ActionMessage("error.show.employees.notfound"));
            // Signalement des erreurs a la page d'origine
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            }
        } else {
            // Place search results in EmployeesShowForm for access by JSP.
            showForm.setResults(results);
        }
        // Transmission a la vue appropriee
        return (mapping.findForward(cible));
    }
    
    public ActionForward deleteEmployee(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {/*
         EmployeeManagement service = new EmployeeManagement();
         boolean results = true;
         DynaBean deleteEmp = (DynaBean) form;

         String ssNum;
         ssNum = (String) request.getParameter("ssNum");
         ArrayList<com.hr.struts.model.entities.Employee> listEmp = service.searchBySsNum(ssNum);
         for (com.hr.struts.model.entities.Employee listEmp1 : listEmp) {
         boolean res;
         res = service.delete(listEmp1);
         if (!res) {
         results = false;
         }
         }

         // Cible par defaut
         String cible = new String("succes");

         // Cible en cas d'echec
         if (results == false) {
         cible = new String("echec");
         ActionMessages errors = new ActionMessages();
         errors.add(null, new ActionMessage("error.delete.employees.false"));
         // Signalement des erreurs a la page d'origine
         if (!errors.isEmpty()) {
         saveErrors(request, errors);
         }
         } else {
         deleteEmp.set("results", results);
         }
         // Transmission a la vue appropriee
         return (mapping.findForward(cible));
         */
        
        return null;
    }
    
    public ActionForward updateEmployee(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        
        IEmployeeManagement service = (IEmployeeManagement) request.getServletContext().getAttribute(SERVICE);
        
        boolean results = true;
        DynaBean updateEmp = (DynaBean) form;
        
        String ssNum, name, phone;
        ssNum = (String) request.getParameter("ssNum");
        name = (String) request.getParameter("name");
        phone = (String) request.getParameter("phone");
        ArrayList<com.hr.struts.model.entities.Employee> listEmp = service.searchByTransfer(1, name);
        
        for (com.hr.struts.model.entities.Employee listEmp1 : listEmp) {
            boolean res;
            res = service.update(listEmp1, name, ssNum, phone);
            if (!res) {
                results = false;
            }
        }

        // Cible par defaut
        String cible = new String("succes");

        // Cible en cas d'echec
        if (results == false) {
            cible = new String("echec");
            ActionMessages errors = new ActionMessages();
            errors.add(null, new ActionMessage("error.update.employees.false"));
            // Signalement des erreurs a la page d'origine
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            }
        } else {
            updateEmp.set("results", results);
        }
        // Transmission a la vue appropriee
        return (mapping.findForward(cible));
    }
    
    public ActionForward searchEmployee(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        IEmployeeManagement service = (IEmployeeManagement) request.getServletContext().getAttribute(SERVICE);

        //this.service.setEmployees(getEmployees(connexion(request, response)));
        ArrayList results = null;
        EmployeeSearchForm searchForm = (EmployeeSearchForm) form;

        // Perform employee search based on the criteria entered. 
        String name = searchForm.getName();
        String ssNum = searchForm.getSsNum();
        String phone = searchForm.getPhone();

        /*
         if (name != null && name.trim().length() > 0) {
         results = service.searchByName(name);
         } else if(ssNum != null && ssNum.trim().length() > 0) {
         results = service.searchBySsNum(ssNum.trim());
         } else {
         results = service.searchByPhone(phone.trim());
         }
         */
        if (name != null && name.trim().length() > 0) {
            results = service.searchByTransfer(1, name);
        } else if (ssNum != null && ssNum.trim().length() > 0) {
            results = service.searchByTransfer(2, ssNum.trim());
        } else {
            results = service.searchByTransfer(3, phone.trim());
        }

        // Place search results in EmployeeSearchForm bean for access in the JSP. 
        searchForm.setResults(results);

        // Forward control to this Action's input page.
        return mapping.getInputForward();
    }
}
