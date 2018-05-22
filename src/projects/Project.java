/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projects;

import entities.Entity;
import java.io.File;
import java.util.Dictionary;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A user-created project with a name, an asset location, an XML file location,
 * and a Dictionary of types that are paired with Dictionarys of entities. This
 * handles the adding of Entities, the adding of Types, and other project
 * functions.
 * @author Scott
 */
@XmlRootElement(name="Project")
@XmlAccessorType(XmlAccessType.FIELD)
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
    @XmlElement(name="entities", type=Entity.class)
    private Dictionary<String, Entity> entities;
    
    /**
     * Instantiates an empty project. This is only used by the JAXB
     * XML serializer, which requires a no-argument constructor.
     */
    public Project() {
        this.name = null;
        this.projectLocation = null;
        this.projectFile = null;
    }
    
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
        //place the entity in this type
        entities.put(entity.getName(), entity);
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

    public Dictionary<String, Entity> getEntities() {
        return entities;
    }

    public void setEntities(Dictionary<String, Entity> entities) {
        this.entities = entities;
    }
    
    
}
