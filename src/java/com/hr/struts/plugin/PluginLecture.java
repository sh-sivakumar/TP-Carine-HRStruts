package com.hr.struts.plugin;

import com.hr.struts.model.IEmployeeManagement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;

import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.action.ActionServlet;

public class PluginLecture implements PlugIn {

    /**
     * creer modele injecter les parametres de connection on place l'instance du
     * modele dans le servlet context
     *
     */
    public static final String SERVICE = "SERVICE";
    private String filePath = null; //retrieves from struts config
    private String EMClass = null;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String f) {
        filePath = f;
    }

    public String getEMClass() {
        return EMClass;
    }

    public void setEMClass(String EMClass) {
        this.EMClass = EMClass;
    }

    @Override
    public void init(ActionServlet servlet, ModuleConfig applicationConfig)
            throws javax.servlet.ServletException {

        System.out.println("---->Le plug-in démarre<----");
        Properties properties = new Properties();

        try {
            //Read file
            FileInputStream fis = new FileInputStream(getFilePath());
            properties.load(fis);

            //Put properties from file into the session (cache memory)
            ServletContext context = servlet.getServletContext();

            IEmployeeManagement service = (IEmployeeManagement) new Factory().instantiate(EMClass);

            //service.setConnectionInfo(properties);
            context.setAttribute(SERVICE, service);

            //Show items' value (test)
            /*
            System.out.println(properties.getProperty("driver", "Sans Driver"));
            System.out.println(properties.getProperty("url", "Sans URL"));
            System.out.println(properties.getProperty("login", "Sans Login"));
            System.out.println(properties.getProperty("pwd", "Sans Password"));
            */

        } catch (FileNotFoundException fnfe) {
            throw new ServletException(fnfe.getMessage());
        } catch (IOException ioe) {
            throw new ServletException(ioe.getMessage());
        }
    }

    @Override
    public void destroy() {
        System.out.println("---->Le plug-in s'arrête<----");
    }
}
