/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projects;

import entities.Entity;
import java.io.File;
import java.util.Dictionary;

/**
 * 
 * @author Scott
 */
public class Project {
    /**
     * The name of the project
     */
    private String name;
    /**
     * The path to the project's top directory
     */
    private File projectFile;
    /**
     * A dictionary of all the categories in this project. New entities should
     * be added to this dictionary by assigning a category, and storing within
     * that category using the name as the key and the
     * entity itself as the element.
     */
    private Dictionary<String, Dictionary<String, Entity>> categories;
    /**
     * A dictionary of all the searchable entities in the current
     * open category (or type).
     */
    private Dictionary<String, Entity> entities;
    
    public Project(String name, File projectFile) {
        this.name = name;
        this.projectFile = projectFile;
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
