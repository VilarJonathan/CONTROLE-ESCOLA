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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.MaskFormatter;
import jeanderson.controller.util.MaskType;

/**
 *
 * @author Jonathan Vilar
 */
public class CadastroContatoController extends Inicializador {

    @FXML
    private CheckBox checkRetorno;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private DatePicker  dataDeRetorno;
    @FXML
    private ComboBox<String> cbCursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listaCursos = FXCollections.observableArrayList();
        listaCursos.add("Micropgmentação");
        listaCursos.add("Design de Barba");
        cbCursos.setItems(listaCursos);
        
        //vou usar uma classe que fiz para formatar o campo Telefone e Data da forma que queremos.
        MaskFormatter formatadorDeCampos = new MaskFormatter();
        formatadorDeCampos.addComponentes(txtTelefone, MaskType.TEL_DIG, true);
        formatadorDeCampos.addComponentes(dataDeRetorno, MaskType.DATA_BARRA_DIG, true);
        
    }

    @Override
    public void clearCampos() {
        //To change body of generated methods, choose Tools | Templates.
        this.checkRetorno.setSelected(false);
        this.txtNome.setText("Carlos Andre");

    }

}
