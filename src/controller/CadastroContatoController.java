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
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.MaskFormatter;
import jeanderson.controller.util.MaskType;
import model.Curso;
import model.dao.BancoDados;
import util.ModelConverters;

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
    private ComboBox<Curso> cbCursos;
    private ObservableList<Curso> lista_de_cursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //aqui usamos um enum para informar qual o tipo de conversão para a exibição do comboBox.
        this.cbCursos.setConverter(ModelConverters.CURSO.getConverter());
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

}
