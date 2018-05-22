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
 * A user-created project with a name, an asset location, an XML file location,
 * and a Dictionary of types that are paired with Dictionarys of entities. This
 * handles the adding of Entities, the adding of Types, and other project
 * functions.
 * @author Scott
 */
public class Project {
    /**
     * The name of the project
     */
    private String name;
    /**
     * The path to the directory containing all the project's assets
     */
    private File projectLocation;
    /**
     * The path to the project's top directory
     */
    private File projectFile;
    /**
     * A dictionary of all the entities in this project. New entities should
     * be added to this dictionary by assigning a category, and storing within
     * that category using the name as the key and the
     * entity itself as the element.
     */
    private Dictionary<String, Dictionary<String, Entity>> entities;
    
    /**
     * Instantiates a new project
     * @param name The name of the project (the user sees this)
     * @param projectLocation The path to the directory containing all the project's assets
     * @param projectFile The path to the project's top directory
     */
    public Project(String name, File projectLocation, File projectFile) {
        this.name = name;
        this.projectLocation = projectLocation;
        this.projectFile = projectFile;
    }
    
    /**
     * Handles adding new entities to the project
     * @param entity 
     */
    public void add(Entity entity) {
        //get the entity's type
        String type = entity.getType();
        //get a reference to the type list of this entity's type
        Dictionary<String, Entity> typeList = entities.get(type);
        //place the entity in this type
        typeList.put(entity.getName(), entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(File projectLocation) {
        this.projectLocation = projectLocation;
    }

    public File getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
    }

    public Dictionary<String, Dictionary<String, Entity>> getEntities() {
        return entities;
    }

    public void setEntities(Dictionary<String, Dictionary<String, Entity>> entities) {
        this.entities = entities;
    }
    
    
}
