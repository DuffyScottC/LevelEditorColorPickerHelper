/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();
    private final ProjectController projectController;
    private final JSplitPane mainSplitPane;

    public Controller() {
        frame.setTitle("Level Editor Color Picker");
        //this is neccessary to prevent the user from closing without saving
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 550);
        
        //initialize spinner value
        frame.getAlphaSpinner().setValue(255);
        
        //Set up the Results List Controller
        ResultsListController resultsListController = new ResultsListController(frame);
        RecentListController recentListController = new RecentListController(frame);
        ModifiedController modifiedController = new ModifiedController(frame);
        ColorPickerController colorPickerController = new ColorPickerController(frame,
            modifiedController);
        SearchController searchController = new SearchController(frame, resultsListController);
        projectController = new ProjectController(frame,
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
        frame.getExportPaletteMenuItem().setEnabled(false);
        
        //set up the type combo box
        frame.getTypeComboBox().removeAllItems();
        frame.getTypeComboBox().addItem(Utils.DEFAULT_TYPE);
        
        //set up the split pane
        mainSplitPane = frame.getMainSplitPane();
        
        loadLayoutPreferences();
        
        frame.getSwapCheckBoxMenuItem().addActionListener((ActionEvent e) -> {
            swapPanels();
        });
        
        frame.getVerticalCheckBoxMenuItem().addActionListener((ActionEvent e) -> {
            if (mainSplitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                switchToVerticalSplit();
            } else {
                switchToHorizontalSplit();
            }
        });
    }

    private void loadLayoutPreferences() {
        //get the projectController's preferences context
        Preferences prefs 
            = Preferences.userRoot().node(projectController.getClass().getName());
        
        boolean layoutVertical = prefs.getBoolean(Utils.LAYOUT_VERTICAL, false);
        frame.getVerticalCheckBoxMenuItem().setSelected(layoutVertical);
        if (layoutVertical) {
            switchToVerticalSplit();
        }
        
        boolean layoutSwap = prefs.getBoolean(Utils.LAYOUT_SWAP, false);
        frame.getSwapCheckBoxMenuItem().setSelected(layoutSwap);
        if (layoutSwap) {
            swapPanels();
        }
        
        int width = prefs.getInt(Utils.LAYOUT_WIDTH, 600);
        int height = prefs.getInt(Utils.LAYOUT_HEIGHT, 550);
        int posX = prefs.getInt(Utils.LAYOUT_POS_X, -1);
        int posY = prefs.getInt(Utils.LAYOUT_POS_Y, -1);
        
        //set the height and width
        frame.setSize(width, height);
        //if this is the first time we've launched
        if (posX == -1 || posY == -1) {
            frame.setLocationRelativeTo(null);
        } else {
            frame.setLocation(posX, posY);
        }
        
        int mainSplitPos 
                = prefs.getInt(Utils.LAYOUT_MAIN_SPLIT_POS, 300);
        frame.getMainSplitPane().setDividerLocation(mainSplitPos);
        
        int listsSplitPos 
                = prefs.getInt(Utils.LAYOUT_LISTS_SPLIT_POS, 300);
        frame.getListsSplitPane().setDividerLocation(listsSplitPos);
    }

    private void swapPanels() {
        Component r = mainSplitPane.getRightComponent();
        Component l = mainSplitPane.getLeftComponent();

        // remove the components
        mainSplitPane.setLeftComponent(null);
        mainSplitPane.setRightComponent(null);

        // add them swapped
        mainSplitPane.setLeftComponent(r);
        mainSplitPane.setRightComponent(l);
    }

    private void switchToVerticalSplit() {
        Component r = mainSplitPane.getRightComponent();
        Component l = mainSplitPane.getLeftComponent();

        // remove the components
        mainSplitPane.setLeftComponent(null);
        mainSplitPane.setRightComponent(null);

        // add them back
        mainSplitPane.setTopComponent(l);
        mainSplitPane.setBottomComponent(r);
        mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainSplitPane.setDividerLocation((double) 0.4);
    }
    
    private void switchToHorizontalSplit() {
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
    }
    
    public static void main(String[] args) {
        
        //handle Command-Q on Mac through window-closing
        System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");
        
        Controller app = new Controller();
        app.frame.setVisible(true);
    }
    
}
