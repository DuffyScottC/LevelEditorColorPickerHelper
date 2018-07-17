/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projects;

import entities.Entity;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
    @XmlTransient
    private File projectLocation = null;
    /**
     * The path to the project's resource directory
     */
    @XmlTransient
    private File projectResourceFolder = null;
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
    
    //MARK: Command
    private final List<String> command = new ArrayList();
    private boolean includeColorHex = false;
    private boolean includeAlphaInHex = false;
    private boolean includeRed = false;
    private boolean includeGreen = false;
    private boolean includeBlue = false;
    private boolean includeAlpha = false;
    private boolean includeName = false;
    private boolean includeType = false;
    private boolean includeUnityPrefab = false;
    private boolean includexOffset = false;
    private boolean includeyOffset = false;
    
    /**
     * The currently selected and displayed entity
     */
    @XmlTransient
    private Entity currentEntity = null;
    
    /**
     * Compares entities by their names
     */
    @XmlTransient
    private Comparator alphabeticalComp = (Object o1, Object o2) -> {
        if (o1 instanceof Entity) {
            if (o2 instanceof Entity) {
                Entity e1 = (Entity) o1;
                Entity e2 = (Entity) o2;
                return e1.getName().compareTo(e2.getName());
            }
        }
        return -1;
    };
    
    /**
     * Instantiates an empty project. This is only used by the JAXB
     * XML serializer, which requires a no-argument constructor.
     */
    public Project() {
        this.name = null;
    }
    
    /**
     * Instantiates a new project
     * @param name The name of the project (the user sees this)
     * @param projectLocation The path to the project's directory
     */
    public Project(String name, File projectLocation) {
        this.name = name;
        this.projectLocation = projectLocation;
    }
    
    /**
     * Handles adding new entities to the project. Adds the new entity to all
     * of the lists.
     * @param entity 
     */
    public void addEntity(Entity entity) {
        //place the entity in the list of all entities
        this.entities.add(entity);
        //sort the entities by name.
        entities.sort(alphabeticalComp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncludeColorHex() {
        return includeColorHex;
    }

    public boolean isIncludeAlphaInHex() {
        return includeAlphaInHex;
    }

    public boolean isIncludeRed() {
        return includeRed;
    }

    public boolean isIncludeGreen() {
        return includeGreen;
    }

    public boolean isIncludeBlue() {
        return includeBlue;
    }

    public boolean isIncludeAlpha() {
        return includeAlpha;
    }

    public boolean isIncludeName() {
        return includeName;
    }

    public boolean isIncludeType() {
        return includeType;
    }

    public boolean isIncludeUnityPrefab() {
        return includeUnityPrefab;
    }

    public boolean isIncludexOffset() {
        return includexOffset;
    }

    public boolean isIncludeyOffset() {
        return includeyOffset;
    }
    
    public void setCommandBooleans(
            boolean includeColorHex,
            boolean includeAlphaInHex,
            boolean includeRed,
            boolean includeGreen,
            boolean includeBlue,
            boolean includeAlpha,
            boolean includeName,
            boolean includeType,
            boolean includeUnityPrefab,
            boolean includexOffset,
            boolean includeyOffset) {
        this.includeColorHex = includeColorHex;
        this.includeAlphaInHex = includeAlphaInHex;
        this.includeRed = includeRed;
        this.includeGreen = includeGreen;
        this.includeBlue = includeBlue;
        this.includeAlpha = includeAlpha;
        this.includeName = includeName;
        this.includeType = includeType;
        this.includeUnityPrefab = includeUnityPrefab;
        this.includexOffset = includexOffset;
        this.includeyOffset = includeyOffset;
    }
    
    public void createProjectLocationAndResourceFolder() {
        Path resourcePath = projectLocation.toPath();
        Path resourceFile = Paths.get(resourcePath.toString(), 
                "Resources");
        projectResourceFolder = resourceFile.toFile();
    }
    
    /**
     * Returns a file object pointing to the file with the given name within
     * the Resource folder, or null if the Resource folder could not be found.
     * @param resourceName
     * @return The resource, or null if the resource folder could not be found.
     */
    public File getResource(String resourceName) {
        if (!projectResourceFolder.exists()) {
            return null;
        }
        Path resourceFolderPath = projectResourceFolder.toPath();
        Path resourcePath = Paths.get(resourceFolderPath.toString(), 
                resourceName);
        return resourcePath.toFile();
    }

    public File getProjectResourceFolder() {
        return projectResourceFolder;
    }
    
    public File getProjectLocation() {
        return projectLocation;
    }
    
    public void setProjectLocation(File projectLocation) {
        this.projectLocation = projectLocation;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities.addAll(entities);
        //sort the entities by name.
        entities.sort(alphabeticalComp);
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
        for (Entity entity : entities) {
            if (entity.getTypeIndex() == index) {
                entity.setTypeIndex(0);
            }
        }
        types.remove(index);
    }

    public List<String> getCommand() {
        return command;
    }

    public void setCommand(List<String> newCommand) {
        command.clear();
        command.addAll(newCommand);
    }
    
    /**
     * Serializes the currentProject to the projectFile using JAXB
     * XML serialization. 
     * @param project The project to serialize
     * @throws JAXBException if something goes wrong in the serialization 
     * process
     */
    public static void serializeProjectToXML(Project project) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Project.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Path projectLocationPath = project.getProjectLocation().toPath();
        File projectFile
                = Paths.get(projectLocationPath.toString(), 
                        project.getName() + ".lecp").toFile();
        marshaller.marshal(project, projectFile);
    }
    
    public static Project deserializeProjectFromXML(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Project.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object obj = unmarshaller.unmarshal(file);
        if (obj instanceof Project) {
            return (Project) obj;
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Name: ");
        s.append(name);
        s.append("\nProject Location: ");
        s.append(projectLocation);
        return s.toString();
    }
    
}
