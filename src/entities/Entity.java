/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Color;

/**
 * An entity consists of an image, a name, and a color.<>
 * These entities are displayed in the jLists, and stored in text files
 * so that they can be edited, shared, etc.
 * @author Scott
 */
public class Entity {
    /**
     * The path of a the image associated with this entity
     */
    private String image;
    /**
     * The name associated with this entity
     */
    private String name;
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
     * The path of the Unity Prefab associated with this entity
     */
    private String unityPrefab;
    
    /**
     * Create a new entity
     * @param image The path of the image associated with this entity
     * @param name The name associated with this entity
     * @param color The color associated with this entity
     * @param unityPrefab The path of the Unity Prefab associated with this entity
     */
    public Entity (String image, String name, Color color, String unityPrefab) {
        this.image = image;
        this.name = name;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.unityPrefab = unityPrefab;
    }
    
    /**
     * Create a new entity
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
    
    public String getImage() {
        return image;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Entity) {
            Entity e = (Entity) obj;
            if (r == e.r && g == e.g && b == e.b) {
                return false;
            } else {
                if (name.equals(e.name)) {
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
}
