/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();

    public Controller() {
        frame.setTitle("Level Editor Color Picker");
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 550);
        
        frame.getAlphaSpinner().setValue(255);
        
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
        
        frame.getAddEntityMenuItem().setEnabled(false);
        frame.getDeleteEntityMenuItem().setEnabled(false);
        frame.getSetCommandMenuItem().setEnabled(false);
        frame.getUseCommandCheckBoxMenuItem().setEnabled(false);
        frame.getGenerateScriptsMenuItem().setEnabled(false);
        
        //set up the type combo box
        frame.getTypeComboBox().removeAllItems();
        frame.getTypeComboBox().addItem(Utils.DEFAULT_TYPE);
        
        //set up the split pane
        JSplitPane mainSplitPane = frame.getMainSplitPane();
        mainSplitPane.setDividerLocation((double) 0.4);
        
        frame.getSwapMenuItem().addActionListener((ActionEvent e) -> {
            Component r = mainSplitPane.getRightComponent();
            Component l = mainSplitPane.getLeftComponent();

            // remove the components
            mainSplitPane.setLeftComponent(null);
            mainSplitPane.setRightComponent(null);

            // add them swapped
            mainSplitPane.setLeftComponent(r);
            mainSplitPane.setRightComponent(l);
        });
        
        frame.getVerticalMenuItem().addActionListener((ActionEvent e) -> {
            if (mainSplitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                Component r = mainSplitPane.getRightComponent();
                Component l = mainSplitPane.getLeftComponent();
                
                // remove the components
                mainSplitPane.setLeftComponent(null);
                mainSplitPane.setRightComponent(null);

                // add them back
                mainSplitPane.setTopComponent(l);
                mainSplitPane.setBottomComponent(r);
                mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
                frame.getVerticalMenuItem().setText("Horizontal Layout");
                mainSplitPane.setDividerLocation((double) 0.4);
            } else {
                Component b = mainSplitPane.getBottomComponent();
                Component t = mainSplitPane.getTopComponent();
                
                // remove the components
                mainSplitPane.setTopComponent(null);
                mainSplitPane.setBottomComponent(null);

                // add them back
                mainSplitPane.setLeftComponent(t);
                mainSplitPane.setRightComponent(b);
                mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
                mainSplitPane.setDividerLocation((double) 0.4);
                frame.getVerticalMenuItem().setText("Vertical Layout");
            }
        });
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }

}
