/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.HomeController;
import javafx.application.Application;
import javafx.stage.Stage;
import jeanderson.controller.control.ControlStage;

/**
 *
 * @author Jonathan Vilar
 */
public class Executor extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ControlStage<HomeController>
                controlHome= ControlStage.newBuilder()
                .addClassController(new HomeController())
                .addNameFromFXML("Home")
                .addTitleStage("Controle de Escola").build();
        controlHome.show();
    }
    
    
}
