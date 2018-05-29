/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projects;

import controllers.Utils;
import entities.Entity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * A user-created project with a name, an asset location, an XML file location,
 * a list of entities, and several HashMaps for searching for entities by specific
 * attributes. This handles the adding of Entities, the adding of Types, and
 * other project functions.
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
     * The path to the project's top directory
     */
    private File projectFile;
    /**
     * The path to the directory containing all the project's assets
     */
    @XmlTransient
    private File projectLocation = null;
    /**
     * A list of all the entities in this project
     */
    @XmlElement(name="entities", type=Entity.class)
    private final List<Entity> entities = new ArrayList();
    /**
     * A list of all the types in this project. There should always be at least
     * one type in this list. When the list is cleared, a type called "Misc"
     * is added. 
     */
    @XmlElement(name="types", type=String.class)
    private final List<String> types = new ArrayList();
    
    /**
     * The currently selected and displayed entity
     */
    @XmlTransient
    private Entity currentEntity = null;
    
    /**
     * Instantiates an empty project. This is only used by the JAXB
     * XML serializer, which requires a no-argument constructor.
     */
    public Project() {
        this.name = null;
        this.projectFile = null;
    }
    
    /**
     * Instantiates a new project
     * @param name The name of the project (the user sees this)
     * @param projectFile The path to the project's top directory
     */
    public Project(String name, File projectFile) {
        this.name = name;
        this.projectFile = projectFile;
        //find the directory where this file is located.
        this.projectLocation = projectFile.getParentFile();
    }
    
    /**
     * Handles adding new entities to the project. Adds the new entity to all
     * of the lists.
     * @param entity 
     */
    public void addEntity(Entity entity) {
        //place the entity in the list of all entities
        this.entities.add(entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createProjectLocation() {
        projectLocation = projectFile.getParentFile();
    }
    
    public File getProjectLocation() {
        return projectLocation;
    }

    public File getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
        this.projectLocation = projectFile.getParentFile();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities.addAll(entities);
    }

    public List<String> getTypes() {
        return types;
    }
    
    public Entity getCurrentEntity() {
        return currentEntity;
    }
    
    /**
     * Assigns the passed in entity to be the currently selected entity.
     * @param currentEntity 
     */
    public void setCurrentEntity(Entity currentEntity) {
        this.currentEntity = currentEntity;
    }
    
    public void removeCurrentEntity() {
        entities.remove(currentEntity);
    }
    
    public void addType(String newType) {
        //add the type to the type list with the true case
        types.add(newType);
    }

    /**
     * Removes the indicated type at index from the project by removing it from
     * the list of types and changing the type of all entities whose type
     * matches the indicated type to the default type.
     * @param index The index of the type to remove
     */
    public void removeType(int index) {
        String typeToRemove = types.get(index);
        for (Entity entity : entities) {
            if (entity.getType().equals(typeToRemove)) {
                entity.setType(Utils.DEFAULT_TYPE);
            }
        }
        types.remove(index);
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Name: ");
        s.append(name);
        s.append("\nProject Location: ");
        s.append(projectLocation);
        s.append("\nProject File: ");
        s.append(projectFile);
        return s.toString();
    }
    
}
