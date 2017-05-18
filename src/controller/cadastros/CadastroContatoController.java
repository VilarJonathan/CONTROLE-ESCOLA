/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cadastros;

import controller.HomeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.MaskFormatter;
import jeanderson.controller.util.MaskType;
import model.Curso;
import factory.BancoDados;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import jeanderson.controller.control.ControlStage;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.util.DialogType;
import model.Contato;
import util.Log;
import util.converters.Converters;
import util.Situacao;

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
    private DatePicker dataDeRetorno;
    @FXML
    private Label lbDataRetorno;
    @FXML
    private ComboBox<Curso> cbCursos;
    @FXML
    private ComboBox<Situacao> cbSituacao;
    @FXML
    private TextArea txtObservacao;
    private ObservableList<Curso> lista_de_cursos;
    //para usar como verificação se iniciou como editMode ou não.
    private boolean isEditMode = false;
    //recebera um contato para usar para update, ou seja será usado no modo de edição.
    private Contato contatoParaEditar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        //aqui usamos um enum para informar qual o tipo de conversão para a exibição do comboBox.
        this.cbCursos.setConverter(Converters.CURSO.getConverter());

        this.cbSituacao.setItems(FXCollections.observableArrayList(Situacao.values()));
        //vou usar uma classe que fiz para formatar o campo Telefone e Data da forma que queremos.
        MaskFormatter formatadorDeCampos = new MaskFormatter();
        formatadorDeCampos.addComponentes(txtTelefone, MaskType.TEL_DIG, true);
        formatadorDeCampos.addComponentes(dataDeRetorno, MaskType.DATA_BARRA_DIG, true);

    }

    @Override
    public void clearCampos() {
        //To change body of generated methods, choose Tools | Templates.
        this.checkRetorno.setSelected(false);
        this.txtNome.setText("");
        this.txtTelefone.setText("");
        this.txtObservacao.setText("");
        //limpa o comboBox para adicionar novas dados.
        this.cbCursos.getSelectionModel().clearSelection();
        this.cbSituacao.getSelectionModel().clearSelection();
        actionCheckRetornar();
        this.isEditMode = false;
    }

    /**
     * Método carrega a lista de cursos, de forma paralela no comboBox.
     */
    public void carregarListaDeCursos() {
        Task<ObservableList> task = new Task<ObservableList>() {
            @Override
            protected ObservableList call() throws Exception {
                return BancoDados.queryAll("curso");
            }

            @Override
            protected void succeeded() {
                //este método só ocorre quando dar sucesso. o metodo getValue retorna o valor q estou passando como retorno na metodo call. logo a cima.
                lista_de_cursos = getValue();
                cbCursos.setItems(lista_de_cursos);
            }

        };
        //este aqui faz executar em paralelo. ou sejá não altera a navegação do usuario.
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @FXML
    public void actionSalvar() {
        if (!this.isEditMode) {
            if (this.verificarCampos()) {
                Contato contato = new Contato();
                this.passarDados(contato);
                if (BancoDados.save(contato)) {
                    clearCampos();
                    DialogFX.showMessage("Contato Salvo com sucesso", "Salvo com Sucesso!", DialogType.SUCESS);
                } else {
                    DialogFX.showMessage("Houve um erro ao salvar contato", "Error ao Salvar!", DialogType.ERRO);
                }
            }
        } else {
           this.passarDados(this.contatoParaEditar);
           if(BancoDados.update(this.contatoParaEditar)){
               DialogFX.showMessage("Contato Editado com sucesso", "Editado com Sucesso!", DialogType.SUCESS);
           }else{
               DialogFX.showMessage("Houve um erro ao editar contato", "Error ao Editar", DialogType.ERRO);
           }
        }
    }

    /**
     * método é chamado no evento de marcar o checkBox. se ativar o checkbox ele
     * mostrar outros campos como data de retorno.
     */
    @FXML
    public void actionCheckRetornar() {
        this.lbDataRetorno.setVisible(this.checkRetorno.isSelected());
        this.dataDeRetorno.setVisible(this.checkRetorno.isSelected());
    }

    private boolean verificarCampos() {
        if (this.txtNome.getText().trim().isEmpty()) {
            return false;
        }
        if (this.txtTelefone.getText().trim().isEmpty()) {
            return false;
        }
        if (this.cbCursos.getSelectionModel().getSelectedIndex() == -1) {
            return false;
        }
        if (this.cbSituacao.getSelectionModel().getSelectedIndex() == -1) {
            return false;
        }
        return true;
    }

    /**
     * É chamado quando a janela é aberta em modo de edição.
     *
     * @param data
     */
    @Override
    public void editMode(Object data) {
        this.isEditMode = true;
        this.contatoParaEditar = (Contato) data;
        this.txtNome.setText(this.contatoParaEditar.getNome());
        this.txtTelefone.setText(this.contatoParaEditar.getTelefone());
        this.txtObservacao.setText(this.contatoParaEditar.getObservacao());
        this.cbSituacao.getSelectionModel().select(this.contatoParaEditar.getSituacao());
        this.cbCursos.getSelectionModel().select(this.contatoParaEditar.getCursoPretendido());
        if (this.contatoParaEditar.getDataRetorno() != null) {
            this.checkRetorno.setSelected(true);
            this.dataDeRetorno.setValue(this.contatoParaEditar.getDataRetorno());
            this.dataDeRetorno.setVisible(true);
            this.lbDataRetorno.setVisible(true);
        }
    }

    /**
     * Método criado para pegar os dados e colocar no contato. Para que não se escreva essa repetição várias vezes.
     * @param contato
     * 
     */
    private void passarDados(Contato contato) {
        contato.setCursoPretendido(this.cbCursos.getValue());
        contato.setNome(this.txtNome.getText().trim());
        contato.setTelefone(this.txtTelefone.getText().trim());
        contato.setSituacao(this.cbSituacao.getValue());
        contato.setObservacao(this.txtObservacao.getText());
        if (this.checkRetorno.isSelected()) {
            contato.setDataRetorno(this.dataDeRetorno.getValue());
        }
    }
    
    @FXML
    public void actionLimpar(){
        this.clearCampos();
    }
    
    @FXML
    public void actionCancelar(){
        try {
            ControlStage<HomeController>  controlHome = ControlStage.getAllSeeControl(HomeController.class);
            controlHome.getController().telaCadastro.getStage().close();            
        } catch (Exception ex) {
           Log.salvaLogger(getClass().getName(), "actionCancelar()", ex);
        }
    }
}
