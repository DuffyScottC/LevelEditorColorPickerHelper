/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Color;
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
@XmlRootElement(name="Entity")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"image","name","typeIndex","r","g","b","a","classIndex","offset"})
public class Entity implements Comparable<Entity> {
    /**
     * The path of a the image associated with this entity. This is null
 if there is no image associated with this entity, and you should
 simply use the color associated with this entity to color the
 image panel.
     */
    private String image;
    /**
     * The name associated with this entity
     */
    private String name;
    /**
     * The name of the type or category associated with this entity
     */
    private int typeIndex;
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
     * The a of the color associated with this entity
     */
    private int a;
    /**
     * The classIndex of this entity. (0=GameObject, 1=TileEntity)
     */
    private int classIndex;
    /**
     * The offset associated with this entity
     */
    private Offset offset;
    /**
     * Instantiates an empty entity. This is only used by the JAXB
     * XML serializer, which requires a no-argument constructor.
     */
    public Entity() {
        this.image = null;
        this.name = null;
        this.typeIndex = 0;
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.a = 255;
        this.classIndex = 0;
        this.offset = Offset.zero;
    }
    
    /**
     * Create a new entity using a Color object to assign the r, g, b values.
     * @param image The path of the image associated with this entity (pass in 
 null if you don't have an image path for this entity, and the entity will
 simply use its own color as the image)
     * @param name The name associated with this entity
     * @param typeIndex The name of the type or category associated with this entity
     * @param color The color associated with this entity
     * @param classIndex The class of this entity (GameObject=0, Tile=0)
     * @param offset The offset of the entity
     */
    public Entity (String image, String name, int typeIndex, 
            Color color, int classIndex, Offset offset) {
        this.image = image;
        this.name = name;
        this.typeIndex = typeIndex;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = color.getAlpha();
        this.classIndex = classIndex;
        this.offset = offset;
    }
    
    public void replaceValues(String image, String name, int typeIndex, 
            Color color, int classIndex, Offset offset) {
        this.image = image;
        this.name = name;
        this.typeIndex = typeIndex;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.a = color.getAlpha();
        this.classIndex = classIndex;
        this.offset = offset;
    }
    
    /**
     * Create a new entity using the r, g, b values directly.
     * @param image The path of the image associated with this entity
     * @param name The name associated with this entity
     * @param r The r of the color associated with this entity
     * @param g The g of the color associated with this entity
     * @param b The b of the color associated with this entity
     * @param classIndex The path of the Unity Prefab associated with this entity
     * @param offset The offset of the Entity
     */
    public Entity (String image, String name, int r, int g, int b, int a, 
            int classIndex, Offset offset) {
        this.image = image;
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.classIndex = classIndex;
        this.offset = offset;
    }
    
    @Override
    public int compareTo(Entity e) {
        //compare the names
        return this.name.compareTo(e.name);
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
            if (this.name.equals(e.name)) {
                if (this.r == e.r && this.g == e.g && this.b == e.b && this.a == e.a) {
                    if (this.classIndex == e.classIndex) {
                        if (this.offset.equals(offset)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * The path of a the image associated with this entity. This is null
 if there is no image associated with this entity, and you should
 simply use the color associated with this entity to color the
 image panel.
     * @return 
     */
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

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    
    public Color getColor() {
        return new Color(r, g, b, a);
    }
    
    public void setColor(Color color) {
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        a = color.getAlpha();
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
