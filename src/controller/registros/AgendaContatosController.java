/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.registros;

import controller.HomeController;
import factory.BancoDados;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.control.ControlStage;
import jeanderson.controller.util.MaskFormatter;
import jeanderson.controller.util.MaskType;
import model.Contato;
import model.Curso;
import util.Log;
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
    private TableColumn<Contato, String> tabelaColunaCurso;
    @FXML
    private TableColumn<Contato, Situacao> tabelaColunaSituacao;
    @FXML
    private TableColumn<Contato, LocalDate> tabelaColunaDataRetorno;
    @FXML
    private TextField txtNomePesquisa;
    @FXML
    private TextField txtTelefonePesquisa;
    @FXML
    private DatePicker dataRetornoPesquisa;
    @FXML
    private ComboBox<Curso> cbCursosPesquisa;
    @FXML
    private ComboBox<Situacao> cbSituacaoPesquisa;
    private FilteredList<Contato> listaParaFiltrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //configurando as colunas para receber os valores corretamente e exibir.
        this.tabelaColunaNome.setCellValueFactory(cell -> cell.getValue().nomeProperty());
        this.tabelaColunaTelefone.setCellValueFactory(cell -> cell.getValue().telefoneProperty());
        this.tabelaColunaDataRetorno.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDataRetorno()));
        this.tabelaColunaDataRetorno.setCellFactory(TextFieldTableCell.forTableColumn(Converters.LOCAL_DATE.getConverter()));
        this.tabelaColunaSituacao.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getSituacao()));
        this.tabelaColunaCurso.setCellValueFactory(cell -> cell.getValue().getCursoPretendido().nomeProperty());
        //colocando um css para o texto ficar no centro que fica mais estilo
        this.tabelaColunaTelefone.setStyle("-fx-alignment: CENTER;");
        this.tabelaColunaDataRetorno.setStyle("-fx-alignment: CENTER;");
        this.tabelaColunaCurso.setStyle("-fx-alignment: CENTER;");
        
        this.cbCursosPesquisa.setConverter(Converters.CURSO.getConverter());
        this.cbSituacaoPesquisa.setItems(FXCollections.observableArrayList(Situacao.values()));

        MaskFormatter formatador = new MaskFormatter();
        formatador.addComponentes(dataRetornoPesquisa, MaskType.DATA_BARRA_DIG, true);
        formatador.addComponentes(txtTelefonePesquisa, MaskType.TEL_DIG, true);

    }

    public void carregarTabela() {
        Task<ObservableList<Contato>> task = new Task<ObservableList<Contato>>() {
            @Override
            protected ObservableList<Contato> call() throws Exception {
                cbCursosPesquisa.setItems(BancoDados.queryAll("curso"));
                return BancoDados.queryAll("contato");
            }

            @Override
            protected void succeeded() {
                listaParaFiltrar = new FilteredList<>(getValue(), contato -> true);
                tabelaContatos.setItems(getValue());
            }

        };
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    /**
     * Método é chamado ao pressionar o botão de filtrar.
     */
    @FXML
    public void filtrarCampos() {
        this.listaParaFiltrar.setPredicate(contato -> filtro(contato));
        SortedList<Contato> listaFiltrada = new SortedList<>(this.listaParaFiltrar);
        listaFiltrada.comparatorProperty().bind(this.tabelaContatos.comparatorProperty());
        this.tabelaContatos.setItems(listaFiltrada);
    }

    /**
     * Método para auxilar o filtro. fazendo a combinações de filtros.
     *
     * @param contato
     * @return
     */
    private boolean filtro(Contato contato) {
        boolean verificador = true;
        if (!this.txtNomePesquisa.getText().isEmpty()) {
            boolean retorno = contato.getNome().toLowerCase().startsWith(this.txtNomePesquisa.getText().toLowerCase());
            verificador = (verificador && retorno);
        }
        if (!this.txtTelefonePesquisa.getText().isEmpty()) {
            boolean retorno = contato.getTelefone().startsWith(this.txtTelefonePesquisa.getText());
            verificador = (verificador && retorno);
        }
        if (!this.dataRetornoPesquisa.getEditor().getText().isEmpty()) {
            if (contato.getDataRetorno() != null) {
                boolean retorno = contato.getDataRetorno().equals(this.dataRetornoPesquisa.getValue());
                verificador = (verificador && retorno);
            } else {
                verificador = (verificador && false);
            }
        }
        if (this.cbCursosPesquisa.getSelectionModel().getSelectedIndex() != -1) {
            boolean retorno = contato.getCursoPretendido().getId() == this.cbCursosPesquisa.getValue().getId();
            verificador = (verificador && retorno);
        }
        if (this.cbSituacaoPesquisa.getSelectionModel().getSelectedIndex() != -1) {
            boolean retorno = contato.getSituacao().equals(this.cbSituacaoPesquisa.getValue());
            verificador = (verificador && retorno);
        }
        return verificador;
    }

    /**
     * reseta todos os filtros. e retorna a tabela.
     */
    @FXML
    public void resetarFiltro() {
        clearCampos();
        this.listaParaFiltrar.setPredicate(contato -> true);
        this.tabelaContatos.setItems(this.listaParaFiltrar);
    }

    @Override
    public void clearCampos() {
        this.txtNomePesquisa.setText("");
        this.txtTelefonePesquisa.setText("");
        this.dataRetornoPesquisa.getEditor().setText("");
        this.cbCursosPesquisa.getSelectionModel().clearSelection();
        this.cbSituacaoPesquisa.getSelectionModel().clearSelection();
    }

    /**
     * Método que é chamado no clique do mouse.
     *
     * @param evento
     */
    @FXML
    public void onMouseClickedTabela(MouseEvent evento) {
        try {
            if (this.tabelaContatos.getSelectionModel().getSelectedIndex() != -1) {
                if (evento.getClickCount() == 2) {
                    Contato contato = this.tabelaContatos.getSelectionModel().getSelectedItem();
                    ControlStage<HomeController> controlTelaHome = ControlStage.getAllSeeControl(HomeController.class);
                    controlTelaHome.getController().telaCadastro.getStage().setTitle("Editar Contato");
                    controlTelaHome.getController().telaCadastro.showEditMode(HomeController.class, contato);
                }
            }
        } catch (Exception ex) {
            Log.salvaLogger(getClass().getName(), "onMouseClickedTabela()", ex);
        }
    }

}
