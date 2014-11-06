/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hr.struts.model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author franckmazzolo
 * @param <Entity>
 */
public interface IManagement<Entity> {
    public boolean add(Entity get);
    public boolean delete(Entity e);
    public ArrayList<Entity> findAll() throws SQLException;
}
