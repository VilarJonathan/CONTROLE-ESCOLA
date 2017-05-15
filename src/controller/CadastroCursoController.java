/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.MaskFormatter;
import jeanderson.controller.util.MaskType;

/**
 *
 * @author Jeanderson
 */
public class CadastroCursoController extends Inicializador {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCarga;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MaskFormatter formatador = new MaskFormatter();
        formatador.addComponentes(txtCarga, MaskType.NUMBER_ONLY, false);
    }
    
}
