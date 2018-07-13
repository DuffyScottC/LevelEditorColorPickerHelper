/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

/**
 * Holds three values together, for convenience.
 * Used for Cell Size and Cell Gap values.
 * 
 * @author Scott
 */
public class Three {
    private double x;
    private double y;
    private double z;
    
    public Three (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
}
