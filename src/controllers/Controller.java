/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.awt.Color;
import java.io.File;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import projects.Project;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();
    private final ResultsListController resultsListController;
    private final RecentListController recentListController;

    public Controller() {
        frame.setTitle(getClass().getSimpleName());
        frame.setLocationRelativeTo(null);
        // you can adjust the size with something like this:
        // frame.setSize(600, 500);
        
        //Set up the Results List Controller
        resultsListController = new ResultsListController(frame);
        recentListController = new RecentListController(frame);
        ProjectController projectController = new ProjectController(frame,
            resultsListController,
            recentListController);
        ColorPickerController colorPickerController = new ColorPickerController(frame);
        
        projectController.enterNewProjectState();
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
