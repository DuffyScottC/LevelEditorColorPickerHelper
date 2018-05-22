/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Color;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * An entity consists of an image, a name, and a color.<>
 * These entities are displayed in the jLists, and stored in text files
 * so that they can be edited, shared, etc.
 * @author Scott
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"image","name","type","r","g","b","unityPrefab","tags"})
public class Entity {
    /**
     * The path of a the image associated with this entity. This is null
     * if there is no image associated with this entity, and you should
     * simply use the color associated with this entity to color the
     * image panel.
     */
    private String image;
    /**
     * The name associated with this entity
     */
    private String name;
    /**
     * The name of the type or category associated with this entity
     */
    private String type;
    /**
     * The r of the color associated with this entity
     */
    private int r;
    /**
     * The g of the color associated with this entity
     */
    private int g;
    /**
     * The b of the color associated with this entity
     */
    private int b;
    /**
     * The path of the Unity Prefab associated with this entity. This is 
     * meant to be in the form of a path to the prefab 
     * (e.g. "Prefabs/Prafabname").
     */
    private String unityPrefab;
    
    /**
     * Instantiates an empty entity. This is only used by the JAXB
     * XML serializer, which requires a no-argument constructor.
     */
    public Entity() {
        this.image = null;
        this.name = null;
        this.type = null;
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.unityPrefab = null;
    }
    
    /**
     * Create a new entity using a Color object to assign the r, g, b values.
     * @param image The path of the image associated with this entity (pass in 
     * null if you don't have an image path for this entity, and the entity will
     * simply use its own color as the image)
     * @param name The name associated with this entity
     * @param type The name of the type or category associated with this entity
     * @param color The color associated with this entity
     * @param unityPrefab The path of the Unity Prefab associated with this entity
     */
    public Entity (String image, String name, String type, Color color, String unityPrefab) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.unityPrefab = unityPrefab;
    }
    
    /**
     * Create a new entity using the r, g, b values directly.
     * @param image The path of the image associated with this entity
     * @param name The name associated with this entity
     * @param r The r of the color associated with this entity
     * @param g The g of the color associated with this entity
     * @param b The b of the color associated with this entity
     * @param unityPrefab The path of the Unity Prefab associated with this entity
     */
    public Entity (String image, String name, int r, int g, int b, String unityPrefab) {
        this.image = image;
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
        this.unityPrefab = unityPrefab;
    }
    
    /**
     * Checks the name, color, and imagePath of the given object, in that order,
     * if the object is an Entity.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Entity) {
            Entity e = (Entity) obj;
            if (name.equals(e.name)) {
                return false;
            } else {
                if (r == e.r && g == e.g && b == e.b) {
                    return false;
                } else {
                    if (image.equals(e.image)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else { //not an Entity
            return false;
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public Color getColor() {
        return new Color(r, g, g);
    }
    
    public void setColor(Color color) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
    }

    public String getUnityPrefab() {
        return unityPrefab;
    }

    public void setUnityPrefab(String unityPrefab) {
        this.unityPrefab = unityPrefab;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
