/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ferramentas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import model.ConfigHibernateXML;
import util.xml.HibernateConfig;

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
    @FXML
    private TextField txtEndereco;
    private HibernateConfig hibernateConfig;
    private ConfigHibernateXML xml;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    public void actionSalvar(){
        this.hibernateConfig.getSessionFactory().setUrlConfig(this.txtNomeDoBanco.getText().trim(), txtEndereco.getText().trim(), txtPorta.getText().trim());
        this.hibernateConfig.getSessionFactory().setUsername(this.txtUsuario.getText().trim());
        this.hibernateConfig.getSessionFactory().setPassword(this.txtSenha.getText().trim());
        this.xml.salvarConfig(this.hibernateConfig);
    }
    @FXML
    public void actionCancelar(){
        
    }
    
    /**
     * Carrega as configuracoes do arquivo xml do hibernate.
     */
    public void carregarConfiguracoes(){
        this.xml = new ConfigHibernateXML();
        this.hibernateConfig = xml.getConfigXMl();
    }
}