package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStage;

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
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void chamarTelaCadastroContato() {
        try {
            telaCadastro.show();

        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
