/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projects;

import entities.Entity;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * The path to the directory containing all the project's assets
     */
    private File projectLocation;
    /**
     * The path to the project's top directory
     */
    private File projectFile;
    /**
     * A list of all the entities in this project
     */
    @XmlElement(name="entities", type=Entity.class)
    private final List<Entity> entities = new ArrayList();
    @XmlTransient
    private final Map<String, Entity> entitiesByName = new HashMap();
    @XmlTransient
    private final Map<Color, Entity> entitiesByColor = new HashMap();
    @XmlTransient
    private final Map<String, Entity> entitiesByUnityPrefab = new HashMap();
    /**
     * A map with the entity type as the key and a list of all the entities
     * of a particular type as the element
     */
    @XmlTransient
    private final Map<String, List<Entity>> entitiesByType = new HashMap();
    
    /**
     * A list of all the types in this project. There should always be at least
     * one type in this list. When the list is cleared, a type called "Misc"
     * is added. 
     */
    private final List<String> types = new ArrayList();
    
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
    public void addEntity(Entity entity) {
        //place the entity in the list of all entities
        this.entities.add(entity);
        //add the entity to each list
        this.entitiesByName.put(entity.getName(), entity);
        
        this.entitiesByColor.put(entity.getColor(), entity);
        this.entitiesByUnityPrefab.put(entity.getUnityPrefab(), entity);
        //get the type list accociated with this entity's type
        List<Entity> theTypeList = this.entitiesByType.get(entity.getType());
        theTypeList.add(entity);
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

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities.addAll(entities);
    }

    public Map<String, Entity> getEntitiesByName() {
        return entitiesByName;
    }

    public Map<Color, Entity> getEntitiesByColor() {
        return entitiesByColor;
    }

    public Map<String, Entity> getEntitiesByUnityPrefab() {
        return entitiesByUnityPrefab;
    }

    public Map<String, List<Entity>> getEntitiesByType() {
        return entitiesByType;
    }

    public List<String> getTypes() {
        return types;
    }
    
    public void addType(String newType) {
        //add the type to the type list
        types.add(newType);
        //create a new list of entities for this type. All entities of the new
        //type will be added to this list.
        List<Entity> newTypeList = new ArrayList();
        //add this newTypeList to the entitiesByType HashMap
        entitiesByType.put(newType, newTypeList);
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
