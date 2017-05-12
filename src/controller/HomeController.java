package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStage;
import util.Log;

/**
 * Classe de controle da Home
 *
 * @author Jonathan Vilar
 */
public class HomeController extends Inicializador {

    private ControlStage<CadastroContatoController> telaCadastro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.telaCadastro = ControlStage.newBuilder()
                    .addClassController(new CadastroContatoController())
                    .addNameFromFXML("CadastroContato").build();

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
            telaCadastro.show();

        } catch (Exception ex) {
            //log será salvo em um arquivo txt dentro do projeto
            Log.salvaLogger(this.getClass().getName(),"chamarTelaCadastroContato()", ex);
        }

    }

}
