/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cadastros;

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
import javafx.scene.control.TextArea;
import model.Contato;
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
        //limpa o comboBox para adicionar novas dados.
        this.cbCursos.getItems().clear();

    }

    /**
     * Método carrega a lista de cursos, de forma paralela no comboBox.
     */
    public void carregarListaDeCursos() {
        Task<ObservableList> task = new Task<ObservableList>() {
            @Override
            protected ObservableList call() throws Exception {
                return BancoDados.pegarTodosDados("curso");
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
    public void actionSalvar(){
        if(this.verificarCampos()){
            Contato contato = new Contato();
            contato.setCursoPretendido(this.cbCursos.getValue());
            contato.setNome(this.txtNome.getText().trim());
            contato.setTelefone(this.txtTelefone.getText().trim());
            contato.setSituacao(this.cbSituacao.getValue());
            contato.setObservacao(this.txtObservacao.getText());
            if(this.checkRetorno.isSelected()){
                contato.setDataRetorno(this.dataDeRetorno.getValue());
            }
            BancoDados.salvar(contato);
        }
    }
    /**
     * método é chamado no evento de marcar o checkBox. se ativar o checkbox ele mostrar outros campos como data de retorno.
     */
    @FXML
    public void actionCheckRetornar(){
        this.lbDataRetorno.setVisible(this.checkRetorno.isSelected());
        this.dataDeRetorno.setVisible(this.checkRetorno.isSelected());
    }
    
    private boolean verificarCampos(){
        if(this.txtNome.getText().trim().isEmpty()){
            return false;
        }
        if(this.txtTelefone.getText().trim().isEmpty()){
            return false;
        }
        if(this.cbCursos.getSelectionModel().getSelectedIndex() == -1){
            return false;
        }
        if(this.cbSituacao.getSelectionModel().getSelectedIndex() == -1){
            return false;
        }
        return true;
    }
}
