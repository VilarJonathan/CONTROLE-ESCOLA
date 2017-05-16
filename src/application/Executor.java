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
import jeanderson.controller.control.ControlStageBuilder;
import util.HibernateUtil;

/**
 *
 * @author Jonathan Vilar
 */
public class Executor extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //utilizamos para o instanciamento a Classe ControlStageBuilder
        ControlStage<HomeController> controlHome = new ControlStageBuilder<>()
                .addClassController(new HomeController())
                //aqui passamos o nome do arquivo FXML que será carregado, podemos passar também sua URL
                .addNameFromFXML("Home")
                //adicionamos um titulo a nossa Tela
                .addTitleStage("Controle de Escola")
                //define que este objeto criado seja visto e consultado em qualquer classe atraves do método estatico. getAllSeeControl.
                .defineAllSee(HomeController.class)
                //o build constroi a instancia , retornando um objeto todo configurado da Classe ControlStage.                
                .build();
        
        //este método faz a chamada da tela.
        
        controlHome.show(null); 
        //devo fazer isso para encerrar o hibernate, se não o programa continua em execução.
        //este método setOnCloseRequest é chamado quando é pedido para fechar a Tela.
        controlHome.getStage().setOnCloseRequest(evento -> {
            //fecho o hibernate.
            HibernateUtil.getSessionFactory().close();
        });
    }

}
