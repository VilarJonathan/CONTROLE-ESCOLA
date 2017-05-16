/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cadastros;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jeanderson.controller.componentes.Inicializador;
import jeanderson.controller.util.DialogFX;
import jeanderson.controller.util.DialogType;
import jeanderson.controller.util.MaskFormatter;
import jeanderson.controller.util.MaskType;
import model.Curso;
import factory.BancoDados;

/**
 *
 * @author Jeanderson
 */
public class CadastroCursoController extends Inicializador {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCarga;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MaskFormatter formatador = new MaskFormatter();
        formatador.addComponentes(txtCarga, MaskType.NUMBER_ONLY, false);
    }

    @Override
    public void clearCampos() {
        this.txtNome.setText("");
        this.txtCarga.setText("");
        this.txtNome.requestFocus();
    }

    @FXML
    public void actionCadastrar() {
        if (this.verificaCampos()) {
            Curso curso = new Curso();
            curso.setNome(this.txtNome.getText().trim());
            curso.setCarga(Integer.parseInt(this.txtCarga.getText()));
            //salva no banco de dados e verifica se deu certo, se deu certo então retorna true, se deu erro retorna false.
            if (BancoDados.salvar(curso)) {
                //exibi uma mensagem falando que deu certo.
                DialogFX.showMessage("Curso adicionado com sucesso", "Sucesso!", DialogType.SUCESS);
                //limpar os campos, pois não queremos adicionar o mesmo curso denovo né?
                clearCampos();
            }
        } else {
            //preciso dizer? kkk o DialogType informa qual o tipo de mensagem, no caso uma mensagem de Atenção.
            DialogFX.showMessage("Por favor verifique se você preencheu os campos corretamente!", "Campos Vázios", DialogType.WARNING);
        }
    }

    /**
     * Método que verifica os campos.
     *
     * @return Retorna verdadeiro de todos os campos estiverem preenchidos.
     */
    private boolean verificaCampos() {
        //getText retorna o texto do TextField, o método trim limpa se tiver muito espaço na frente. e o metodo isEmpty informa se está vázio.
        if (this.txtNome.getText().trim().isEmpty()) {
            //significa que o campo está vázio,
            return false;
        }
        if (this.txtCarga.getText().isEmpty()) {
            return false;
        }
        //caso esteja tudo OK retornamos true.
        return true;
    }

}
