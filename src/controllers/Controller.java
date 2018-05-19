/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();

    public Controller() throws URISyntaxException {
        frame.setTitle(getClass().getSimpleName());
        frame.setLocationRelativeTo(null);
        // you can adjust the size with something like this:
        // frame.setSize(600, 500);
        
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File(Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath());
    }

    public static void main(String[] args) {
        Controller app;
        try {
            app = new Controller();
            app.frame.setVisible(true);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
