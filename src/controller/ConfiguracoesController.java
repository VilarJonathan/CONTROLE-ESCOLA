/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;

/**
 *
 * @author Jeanderson
 */
public class ConfiguracoesController extends Inicializador {

    @FXML
    private TextField txtNomeDoBanco;
    @FXML
    private TextField txtPorta;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    public void actionSalvar(){
        
    }
    @FXML
    public void actionCancelar(){
        
    }
}
