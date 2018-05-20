/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Entity;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import views.EntityListRenderer;
import views.MainFrame;

/**
 *
 * @author Scott
 */
public class RecentListController {
    private final DefaultListModel recentListModel = new DefaultListModel();
    private final JList recentList;
    private final List<Entity> entitiesInRecent;
    
    public RecentListController(MainFrame frame, List<Entity> entitiesInRecent) {
        recentList = frame.getRecentList();
        recentList.setModel(recentListModel);
        this.entitiesInRecent = entitiesInRecent;
        recentList.setCellRenderer(new EntityListRenderer(this.entitiesInRecent));
    }
}
