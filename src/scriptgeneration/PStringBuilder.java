/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptgeneration;

/**
 *
 * @author Scott
 */
public class PStringBuilder {
    
    StringBuilder s;
    
    public PStringBuilder () {
        this.s = new StringBuilder();
    }
    
    public void append(Object input) {
        s.append(input);
        System.out.println("----------------------------------------");
        System.out.println(s);
    }
    
    public StringBuilder getStringBuilder() {
        return s;
    }
}
