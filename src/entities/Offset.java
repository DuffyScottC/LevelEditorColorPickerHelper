/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Stores the offset of the entity.
 * @author Scott
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Offset {
    private double x;
    private double y;
    
    /**
     * A shortcut for the offset (0,0)
     */
    public static final Offset zero = new Offset();
    
    public Offset() {
        x = 0;
        y = 0;
    }
    
    public Offset(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        //e.g. (101, 30)
        s.append("(");
        s.append(x);
        s.append(", ");
        s.append(y);
        s.append(")");
        return s.toString();
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Offset) {
            Offset o = (Offset) obj;
            if (this.x == o.x) {
                if (this.y == o.y) {
                    return true;
                }
            }
        }
        return false;
    }
}
