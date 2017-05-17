/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.registros;

import factory.BancoDados;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import jeanderson.controller.componentes.Inicializador;
import model.Contato;
import model.Curso;
import util.Situacao;
import util.converters.Converters;

/**
 *
 * @author Jeanderson
 */
public class AgendaContatosController extends Inicializador {
    @FXML
    private TableView<Contato> tabelaContatos;
    @FXML
    private TableColumn<Contato, String> tabelaColunaNome;
    @FXML
    private TableColumn<Contato, String> tabelaColunaTelefone;
    @FXML
    private TableColumn<Curso, String> tabelaColunaCurso;
    @FXML
    private TableColumn<Situacao,String> tabelaColunaSituacao;
    @FXML
    private TableColumn<Contato,LocalDate> tabelaColunaDataRetorno;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //configurando as colunas para receber os valores corretamente e exibir.
        this.tabelaColunaNome.setCellValueFactory(cell -> cell.getValue().nomeProperty());
        this.tabelaColunaDataRetorno.setCellValueFactory(TextFieldTableCell.forTableColumn(Converters.LOCAL_DATE.getConverter()));
        this.tabelaColunaTelefone.setCellValueFactory(cell -> cell.getValue().telefoneProperty());
        this.tabelaColunaSituacao.setCellValueFactory(TextFieldTableCell.forTableColumn(Converters.ENUM_SITUACAO.getConverter()));
        this.tabelaColunaCurso.setCellValueFactory(cell -> cell.getValue().nomeProperty());
    }
    
    public void carregarTabela(){
        Task<ObservableList<Contato>> task = new Task<ObservableList<Contato>>() {
            @Override
            protected ObservableList<Contato> call() throws Exception {
                return BancoDados.pegarTodosDados("contato");
            }

            @Override
            protected void succeeded() {
                tabelaContatos.setItems(getValue());
            }
            
        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}
