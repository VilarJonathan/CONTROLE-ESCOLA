/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ferramentas;

import controller.HomeController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStage;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.util.DialogType;
import model.ConfigHibernateXML;
import org.hibernate.HibernateException;
import util.HibernateUtil;
import util.Log;
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
    public void actionSalvar() {
        if (verificarCampos()) {
            this.hibernateConfig.getSessionFactory().setUrlConfig(this.txtNomeDoBanco.getText().trim(), txtEndereco.getText().trim(), txtPorta.getText().trim());
            this.hibernateConfig.getSessionFactory().setUsername(this.txtUsuario.getText().trim());
            this.hibernateConfig.getSessionFactory().setPassword(this.txtSenha.getText().trim());
            this.xml.salvarConfig(this.hibernateConfig);
            if (DialogFX.showConfirmation("Configurações Salvas! É necessário reiniciar para que tenha efeito!\nDeseja fechar agora?", "Necessário Reinicialização.")) {

                try {
                    ControlStage<HomeController> controlHome = ControlStage.getAllSeeControl(HomeController.class);
                    controlHome.getStage().close();
                    HibernateUtil.getSessionFactory().close();
                } catch (Exception ex) {
                    Log.salvaLogger(getClass().getName(), "actionCancelar()", ex);
                } catch (NoClassDefFoundError e) {
                    Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, e);
                    System.exit(0);
                }
            }
        } else {
            DialogFX.showMessage("Verifique os campos! Obs:Somente o campo senha não é obrigatorio!", "Falta Preencher Campos", DialogType.WARNING);
        }
    }

    @FXML
    public void actionCancelar() {
        ControlStage<HomeController> controlHome;
        try {
            controlHome = ControlStage.getAllSeeControl(HomeController.class);
            controlHome.getController().telaConfiguracao.getStage().close();
        } catch (Exception ex) {
            Log.salvaLogger(getClass().getName(), "actionCancelar()", ex);
        }

    }

    /**
     * Carrega as configuracoes do arquivo xml do hibernate.
     */
    public void carregarConfiguracoes() {
        this.xml = new ConfigHibernateXML();
        this.hibernateConfig = xml.getConfigXMl();
    }

    private boolean verificarCampos() {
        if (this.txtNomeDoBanco.getText().trim().isEmpty()) {
            return false;
        }
        if (this.txtEndereco.getText().trim().isEmpty()) {
            return false;
        }
        if (this.txtPorta.getText().isEmpty()) {
            return false;
        }
        if (this.txtUsuario.getText().trim().isEmpty()) {
            return false;
        }
        return false;
    }
}
