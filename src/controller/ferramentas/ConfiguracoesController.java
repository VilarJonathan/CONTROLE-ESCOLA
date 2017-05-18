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
        this.hibernateConfig.getSessionFactory().setUrlConfig(this.txtNomeDoBanco.getText().trim(), txtEndereco.getText().trim(), txtPorta.getText().trim());
        this.hibernateConfig.getSessionFactory().setUsername(this.txtUsuario.getText().trim());
        this.hibernateConfig.getSessionFactory().setPassword(this.txtSenha.getText().trim());
        this.xml.salvarConfig(this.hibernateConfig);
        if (DialogFX.showConfirmation("Configurações Salvas! É necessário reiniciar para que tenha efeito!\n Deseja fechar agora?", "Necessário Reinicialização.")) {
            
            try {
                ControlStage<HomeController> controlHome = ControlStage.getAllSeeControl(HomeController.class);
                controlHome.getStage().close();
                HibernateUtil.getSessionFactory().close();
            } catch (Exception ex) {
                Log.salvaLogger(getClass().getName(), "actionCancelar()", ex);
            }catch(NoClassDefFoundError e){
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, e);
                System.exit(0);
            }
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
}
