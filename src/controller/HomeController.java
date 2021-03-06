package controller;

import controller.ferramentas.ConfiguracoesController;
import controller.cadastros.CadastroContatoController;
import controller.registros.AgendaContatosController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
    //componentes da TelaHome.
    @FXML
    private Circle circuloStatus;
    @FXML
    private Label lbStatus;

    //alteradas para public para outras janelas terem acesso.
    public ControlStage<CadastroContatoController> telaCadastro;
    public ControlStage<CadastroContatoController> telaCadastroCurso;
    public ControlStage<ConfiguracoesController> telaConfiguracao;
    public ControlStage<AgendaContatosController> telaAgendaContatos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            this.telaCadastro = ControlStage.newBuilder()
                    .addClassController(new CadastroContatoController())
                    .addTitleStage("Cadastro de Contatos")
                    .addNameFromFXML("CadastroContato").build();
            this.telaCadastroCurso = new ControlStageBuilder<>()
                    .addClassController(new CadastroContatoController())
                    .addNameFromFXML("CadastroCurso")
                    .addTitleStage("Cadastro de Cursos").build();
            this.telaConfiguracao = new ControlStageBuilder<>()
                    .addClassController(new ConfiguracoesController())
                    .addNameFromFXML("Configuracoes")
                    .addTitleStage("Configuração do Banco de Dados")
                    .build();
            this.telaAgendaContatos = new ControlStageBuilder<>()
                    .addClassController(new AgendaContatosController())
                    .addNameFromFXML("AgendaContatos")
                    .addTitleStage("Agenda de Contatos")
                    .build();

        } catch (Exception ex) {
            //log será salvo em um arquivo txt dentro do projeto
            Log.salvaLogger(this.getClass().getName(), "initialize()", ex);
        }

    }

    /**
     * Método é chamado no clique do menuitem Contatos. Faz a exibição da Tela
     * de cadastro.
     */
    @FXML
    public void chamarTelaCadastroContato() {
        try {
            telaCadastro.show(HomeController.class);
            //carrega a lista de cursos.
            this.telaCadastro.getController().carregarListaDeCursos();
        } catch (Exception ex) {
            //log será salvo em um arquivo txt dentro do projeto
            Log.salvaLogger(this.getClass().getName(), "chamarTelaCadastroContato()", ex);
        }

    }

    /**
     * Método chama a Tela de Cadastro de Cursos.
     */
    @FXML
    public void chamarTelaCadastroCurso() {
        try {
            this.telaCadastroCurso.show(HomeController.class);
        } catch (Exception ex) {
            Log.salvaLogger(this.getClass().getName(), "chamarTelaCadastroCurso()", ex);
        }
    }
    
    /**
     * Método chama a tela de configurações.
     */
    @FXML
    public void chamarTelaConfiguracoes(){
        try {
            this.telaConfiguracao.show(HomeController.class);
            this.telaConfiguracao.getController().carregarConfiguracoes();
        } catch (Exception ex) {
            Log.salvaLogger(this.getClass().getName(), "chamarTelaConfiguracoes()", ex);
        }
    }
    
    /**
     *  Método chama a tela de agenda de contatos.
     */
    @FXML
    public void chamarTelaAgendaContatos(){
        try {
            this.telaAgendaContatos.show(HomeController.class);
            this.telaAgendaContatos.getController().carregarTabela();
        } catch (Exception ex) {
            Log.salvaLogger(this.getClass().getName(), "chamarTelaConfiguracoes()", ex);
        }
    }

    /**
     * Método altera o texto do label ao lado do circle, assim como a cor do circle também.
     * @param msg
     * @param color 
     */
    public void atualizarStatus(String msg, Paint color){
        this.lbStatus.setText(msg);
        this.circuloStatus.setFill(color);
    }
}
