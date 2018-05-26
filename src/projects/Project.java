/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projects;

import entities.Entity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    /**
     * Keys are lowercase for convenience and more accurate searching.
     */
    @XmlTransient
    private final Map<String, Entity> entitiesByName = new TreeMap();
    /**
     * Accesses entities by the color value of the desired color.
     * Keys are lowercase for convenience and more accurate searching.
     */
    @XmlTransient
    private final Map<Integer, Entity> entitiesByColorValue = new TreeMap();
    /**
     * Keys are lowercase for convenience and more accurate searching.
     */
    @XmlTransient
    private final Map<String, Entity> entitiesByUnityPrefab = new TreeMap();
    /**
     * A map with the entity type as the key and a list of all the entities
     * of a particular type as the element
     */
    @XmlTransient
    private final Map<String, List<Entity>> entitiesByType = new TreeMap();
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
     * This must be called every time a project is opened, because the transient
     * lists are not serialized for the sake of keeping the .lecp files small.
     * All this does is add all the entities in the entities list to the
     * transient TreeMaps. 
     */
    public void setUpTransientLists() {
        //first add all the type lists to the entitiesByType map
        for (String t : types) {
            //make the type lowercase
            String type = t.toLowerCase();
            //create a new list for this type
            List<Entity> typeList = new ArrayList();
            //add this list to the enititesByType map with the type as the key
            entitiesByType.put(type, typeList);
        }
        //loop through all the entities in the list
        for (Entity entity : entities) {
            //add this entity to the transient lists
            addEntityToTransientLists(entity);
        }
    }
    
    /**
     * Handles adding new entities to the project. Adds the new entity to all
     * of the lists.
     * @param entity 
     */
    public void addEntity(Entity entity) {
        //place the entity in the list of all entities
        this.entities.add(entity);
        addEntityToTransientLists(entity);
    }
    
    /**
     * Adds the passed in entity to all the transient lists in the project class.
     * These lists are not serialized, and are re-created from scratch using the
     * main entities List every time the project is opened. 
     * @param entity 
     */
    private void addEntityToTransientLists(Entity entity) {
        //Add the entity to each list:
        String newName = entity.getName().toLowerCase();
        this.entitiesByName.put(newName, entity);
        
        int newColorValue = entity.getColor().getRGB();
        this.entitiesByColorValue.put(newColorValue, entity);
        
        String newUnityPrefab = entity.getUnityPrefab().toLowerCase();
        this.entitiesByUnityPrefab.put(newUnityPrefab, entity);
        
        //get the type list accociated with this entity's type
        String type = entity.getType().toLowerCase();
        List<Entity> theTypeList = this.entitiesByType.get(type);
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

    public Map<Integer, Entity> getEntitiesByColorValue() {
        return entitiesByColorValue;
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
    
    public void addType(String newType) {
        //add the type to the type list with the true case
        types.add(newType);
        //make the type lowercase
        String newTypeLower = newType.toLowerCase();
        //create a new list of entities for this type. All entities of the new
        //type will be added to this list.
        List<Entity> newTypeList = new ArrayList();
        //add this newTypeList to the entitiesByType HashMap
        entitiesByType.put(newTypeLower, newTypeList);
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
