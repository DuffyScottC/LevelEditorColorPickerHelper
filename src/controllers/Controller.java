/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();
    private boolean swapped = false;

    public Controller() {
        frame.setTitle("Level Editor Color Picker");
        frame.setLocationRelativeTo(null);
        frame.setSize(550, 550);
        
        //Set up the Results List Controller
        ResultsListController resultsListController = new ResultsListController(frame);
        RecentListController recentListController = new RecentListController(frame);
        ModifiedController modifiedController = new ModifiedController(frame);
        ColorPickerController colorPickerController = new ColorPickerController(frame,
            modifiedController);
        SearchController searchController = new SearchController(frame, resultsListController);
        ProjectController projectController = new ProjectController(frame,
            resultsListController,
            recentListController,
            colorPickerController,
            searchController,
            modifiedController);
        
        projectController.enterNewProjectState();
        
        //set up the type combo box
        frame.getTypeComboBox().removeAllItems();
        frame.getTypeComboBox().addItem(Utils.defaultType);
        
        JSplitPane mainSplitPane = frame.getMainSplitPane();
        mainSplitPane.setDividerLocation((double) 0.4);
        
        frame.getSwapMenuItem().addActionListener((ActionEvent e) -> {
            if (mainSplitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT) {
                if (swapped) {
                    mainSplitPane.setTopComponent(frame.getSelectionPanel());
                    mainSplitPane.setBottomComponent(frame.getInfoPanel());
                } else {
                    mainSplitPane.setTopComponent(frame.getInfoPanel());
                    mainSplitPane.setBottomComponent(frame.getSelectionPanel());
                }
            } else {
                if (swapped) {
                    mainSplitPane.setLeftComponent(frame.getSelectionPanel());
                    mainSplitPane.setRightComponent(frame.getInfoPanel());
                } else {
                    mainSplitPane.setLeftComponent(frame.getInfoPanel());
                    mainSplitPane.setRightComponent(frame.getSelectionPanel());
                }
            }
            //toggle the swapped boolean
            swapped = !swapped;
        });
        
        frame.getVerticalMenuItem().addActionListener((ActionEvent e) -> {
            if (mainSplitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT) {
                mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            } else {
                mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            }
        });
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
