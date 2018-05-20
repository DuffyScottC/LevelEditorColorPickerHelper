/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import views.EntityListRenderer;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();

    public Controller() throws URISyntaxException {
        frame.setTitle(getClass().getSimpleName());
        frame.setLocationRelativeTo(null);
        // you can adjust the size with something like this:
        // frame.setSize(600, 500);
        
        String imagePath = System.getProperty("user.dir") + "/resources/images/brick.png";
        
        Entity e = new Entity(imagePath, "name", Color.black, "prefab");
        
        List<Entity> entitiesInResults = new ArrayList();
        entitiesInResults.add(e);
        
        DefaultListModel listModel = new DefaultListModel();
        
        JList list = frame.getResultsList();
        list.setModel(listModel);
        list.setCellRenderer(new EntityListRenderer(entitiesInResults));
        listModel.addElement("name");
        
        File imageFile = new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage ret = new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB);
        ret.getGraphics().drawImage(image,0,0,32,32,null);
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
