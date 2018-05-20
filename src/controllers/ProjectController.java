/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 * Handles the creation, storage, modification, and other management of all
 * projects.
 * @author Scott
 */
public class ProjectController {
    /**
     * The name of the project
     */
    private String name;
    /**
     * The path to the project's top directory
     */
    private String projectPath;
    
    public ProjectController(String name, String projectPath) {
        this.name = name;
        this.projectPath = projectPath;
        //System.getProperty("user.dir") + "/projects/project1/brick.png";
    }
}
