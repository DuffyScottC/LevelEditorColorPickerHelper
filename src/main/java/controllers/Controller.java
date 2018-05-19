/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import entities.Entity;
import java.awt.Color;
import views.MainFrame;

public class Controller {

    private final MainFrame frame = new MainFrame();

    public Controller() {
        frame.setTitle(getClass().getSimpleName());
        frame.setLocationRelativeTo(null);
        // you can adjust the size with something like this:
        // frame.setSize(600, 500);
        Entity e = new Entity("image.png", "Enemy", Color.blue);
        Gson gson = new Gson();
        String eGson = gson.toJson(e);
        System.out.println(eGson);
        System.out.println(Color.blue + " " + Color.blue.toString());
    }

    public static void main(String[] args) {
        Controller app = new Controller();
        app.frame.setVisible(true);
    }
}
