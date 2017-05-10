/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;

/**
 *
 * @author Jonathan Vilar
 */
public class CadastroContatoController extends Inicializador{
    @FXML
    private CheckBox checkRetorno;
    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox<String> cbCursos;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listaCursos = FXCollections.observableArrayList();
        listaCursos.add("Micropgmentação");
        listaCursos.add("Design de Barba");
        cbCursos.setItems(listaCursos);
    }

    @Override
    public void clearCampos() {
        //To change body of generated methods, choose Tools | Templates.
        this.checkRetorno.setSelected(false);
        this.txtNome.setText("Carlos Andre");
        
    }
    
}
