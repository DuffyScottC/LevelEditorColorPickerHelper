/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.util.Dictionary;

/**
 * Handles the creation, storage, modification, and other management of all
 * projects.
 * @author Scott
 */
public class ProjectController {
    /**
     * The name of the project
     */
    private String name;
    /**
     * The path to the project's top directory
     */
    private String projectPath;
    /**
     * A dictionary of all the entities in this project. New entities should
     * be added to this dictionary with the name as the String and the
     * entity itself as the Entity.
     */
    private Dictionary<String, Entity> entities;
    
    public ProjectController(String name, String projectPath) {
        this.name = name;
        this.projectPath = projectPath;
        //System.getProperty("user.dir") + "/projects/project1/brick.png";
    }
    
    /**
     * Handles adding new entities to the project
     * @param entity 
     */
    public void add(Entity entity) {
        entities.put(entity.getName(), entity);
    }
}
