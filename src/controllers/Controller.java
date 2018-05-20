/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();

    public Controller() {
        frame.setTitle(getClass().getSimpleName());
        frame.setLocationRelativeTo(null);
        // you can adjust the size with something like this:
        // frame.setSize(600, 500);
        
        //Set up the Results List Controller
        ResultsListController resultsListController = new ResultsListController(frame);
        RecentListController recentListController = new RecentListController(frame);
        ProjectController projectController = new ProjectController();
        ColorPickerManager colorPickerManager = new ColorPickerManager(frame);
        
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }
}
