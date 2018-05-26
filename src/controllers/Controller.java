/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();
    private final ResultsListController resultsListController;
    private final RecentListController recentListController;

    public Controller() {
        frame.setTitle("Level Editor Color Picker");
        frame.setLocationRelativeTo(null);
        // you can adjust the size with something like this:
        // frame.setSize(600, 500);
        
        //Set up the Results List Controller
        resultsListController = new ResultsListController(frame);
        recentListController = new RecentListController(frame);
        ColorPickerController colorPickerController = new ColorPickerController(frame);
        ProjectController projectController = new ProjectController(frame,
            resultsListController,
            recentListController,
            colorPickerController);
        
        projectController.enterNewProjectState();
        
        //set up the type combo box
        frame.getTypeComboBox().removeAllItems();
        frame.getTypeComboBox().addItem(Utils.defaultType);
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
