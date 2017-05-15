/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jeanderson
 */
public class Contato {

    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty telefone;
    private StringProperty observacao;
    private LocalDate data_retorno;
    private Curso curso_pretendido;
    
    public Contato() {
        this.id = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.telefone = new SimpleStringProperty();
    }
    
    public Integer getId() {
        return this.id.get();
    }
    
    public String getNome() {
        return nome.get();
    }
    
    public String getTelefone() {
        return telefone.get();
    }
    
    public String getObservacao() {
        return observacao.get();
    }
    
    public IntegerProperty getIdProperty() {
        return id;
    }
    
    public StringProperty getNomeProperty() {
        return nome;
    }
    
    public StringProperty getTelefoneProperty() {
        return telefone;
    }
    
    public StringProperty getObservacaoProperty() {
        return observacao;
    }
    
    public LocalDate getData_retorno() {
        return data_retorno;
    }
    
    public Curso getCurso_pretendido() {
        return curso_pretendido;
    }
    
    public void setId(int id) {
        this.id.set(id);
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public void setObservacao(String observacao) {
        this.observacao.set(observacao);
    }
}
