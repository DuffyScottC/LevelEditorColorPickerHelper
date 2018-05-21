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
        
        enterNoProjectState();
        
        
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

    private void enterNoProjectState() {
        resultsListController.clearEntities();
        recentListController.clearEntities();
        frame.getTypeComboBox().setEnabled(false);
        frame.getSearchTextField().setEnabled(false);
        frame.getSelectButton().setEnabled(false);
        frame.getColorCodeTextField().setEnabled(false);
        frame.getIncludeHashTagCheckBox().setEnabled(false);
        frame.getRedSlider().setEnabled(false);
        frame.getRedSpinner().setEnabled(false);
        frame.getGreenSlider().setEnabled(false);
        frame.getGreenSpinner().setEnabled(false);
        frame.getBlueSlider().setEnabled(false);
        frame.getBlueSpinner().setEnabled(false);
        frame.getTagsTextField().setEnabled(false);
        frame.getRevertButton().setEnabled(false);
        frame.getApplyButton().setEnabled(false);
    }
}
