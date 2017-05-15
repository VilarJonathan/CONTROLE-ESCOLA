package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStage;
import jeanderson.controller.control.ControlStageBuilder;
import util.Log;

/**
 * Classe de controle da Home
 *
 * @author Jonathan Vilar
 */
public class HomeController extends Inicializador {

    private ControlStage<CadastroContatoController> telaCadastro;
    private ControlStage<CadastroContatoController> telaCadastroCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("aconteceu");
        try {
            this.telaCadastro = ControlStage.newBuilder()
                    .addClassController(new CadastroContatoController())
                    .addNameFromFXML("CadastroContato").build();
            this.telaCadastroCurso = new ControlStageBuilder<>()
                    .addClassController(new CadastroContatoController())
                    .addNameFromFXML("CadastroCurso")
                    .addTitleStage("Cadastro de Cursos").build();

        } catch (Exception ex) {
            //log será salvo em um arquivo txt dentro do projeto
            Log.salvaLogger(this.getClass().getName(),"initialize()", ex);
        }

    }

    /**
     * Método é chamado no clique do menuitem Contatos.
     * Faz a exibição da Tela de cadastro.
     */
    @FXML
    public void chamarTelaCadastroContato() {
        try {
            telaCadastro.show(HomeController.class);

        } catch (Exception ex) {
            //log será salvo em um arquivo txt dentro do projeto
            Log.salvaLogger(this.getClass().getName(),"chamarTelaCadastroContato()", ex);
        }

    }
    /**
     * Método chama a Tela de Cadastro de Cursos.
     */
    @FXML
    public void chamarTelaCadastroCurso(){
        try {
            this.telaCadastroCurso.show(HomeController.class);
        } catch (Exception ex) {
            Log.salvaLogger(this.getClass().getName(),"chamarTelaCadastroCurso()", ex);
        }
    }

}
