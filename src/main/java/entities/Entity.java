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
     * The color associated with this entity
     */
    private Color color;
    /**
     * The Unity Prefab associated with this entity
     */
    private String unityPrefab;
    
    /**
     * Create a new entity
     * @param image The path of the image associated with this entity
     * @param name The name associated with this entity
     * @param color The color associated with this entity
     * @param unityPrefab The Unity Prefab associated with this entity
     */
    public Entity (String image, String name, Color color, String unityPrefab) {
        this.image = image;
        this.name = name;
        this.color = color;
        this.unityPrefab = unityPrefab;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Entity) {
            Entity e = (Entity) obj;
            if (color.equals(e.color)) {
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
