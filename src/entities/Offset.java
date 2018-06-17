/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 * Stores the offset of the entity.
 * @author Scott
 */
public class Offset {
    private float x;
    private float y;
    
    public Offset() {
        x = 0;
        y = 0;
    }
    
    public Offset(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
