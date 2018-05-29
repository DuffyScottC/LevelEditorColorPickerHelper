/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
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
        frame.getTypeComboBox().addItem(Utils.DEFAULT_TYPE);
        
        JSplitPane mainSplitPane = frame.getMainSplitPane();
        mainSplitPane.setDividerLocation((double) 0.4);
        JPanel selectionPanel = frame.getSelectionPanel();
        JPanel infoPanel = frame.getInfoPanel();
        
        frame.getSwapMenuItem().addActionListener((ActionEvent e) -> {
            mainSplitPane.removeAll();
            if (swapped) {
                //selection left or top
                mainSplitPane.setLeftComponent(selectionPanel);
                mainSplitPane.setRightComponent(infoPanel);
            } else {
                //info left or top
                mainSplitPane.setLeftComponent(infoPanel);
                mainSplitPane.setRightComponent(selectionPanel);
            }
            //toggle the swapped boolean
            swapped = !swapped;
            mainSplitPane.setDividerLocation((double) 0.4);
        });
        
        frame.getVerticalMenuItem().addActionListener((ActionEvent e) -> {
            if (mainSplitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT) {
                mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
            } else {
                mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            }
            mainSplitPane.setDividerLocation((double) 0.4);
        });
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
