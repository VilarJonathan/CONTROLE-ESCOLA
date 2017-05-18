/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import controller.HomeController;
import factory.BancoDados;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import jeanderson.controller.control.ControlStage;
import jeanderson.controller.control.ControlStageBuilder;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.util.DialogType;
import org.hibernate.HibernateException;
import util.HibernateUtil;

/**
 *
 * @author Jonathan Vilar
 */
public class Executor extends Application {
    private  ControlStage<HomeController> controlHome;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //utilizamos para o instanciamento a Classe ControlStageBuilder
        this.controlHome = new ControlStageBuilder<>(primaryStage)
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
        this.controlHome.show(null);        
        this.iniciarConfiguracoes();
    }
    private void iniciarConfiguracoes(){
        //vou inicializar o hibernate de modo paralelo, ou sejá não mexe no desempenho do usuario, se não usasse em paralelo, o tela ia travar até terminar o processo.
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return BancoDados.inicializar();
            }

            @Override
            protected void succeeded() {
                if(!getValue()){
                    DialogFX.showMessage("Houve um erro ao iniciar o banco de dados", "Erro ao iniciar Banco de Dados", DialogType.ERRO);
                }
            }
            
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
        
        this.controlHome.getStage().setOnCloseRequest(evento ->{fecharConexaoBanco();});
    }
    
    private void fecharConexaoBanco(){
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
               try{
                   HibernateUtil.getSessionFactory().close();
                   return true;
               }catch(HibernateException ex){
                   System.err.println(ex);
                   return false;
               }
            }

            @Override
            protected void succeeded() {
                if(!getValue()){
                    System.err.println("Houve um erro ao fechar conexao com o banco de dados.");
                }
            }
            
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}
