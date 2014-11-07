/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.plugin;

/**
 *
 * @author franckmazzolo
 */
public class Factory {

    public Object instantiate(String NomModelClass) {
        try {
            Class cls = Class.forName(NomModelClass);
            Object obj = cls.newInstance();
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

}
