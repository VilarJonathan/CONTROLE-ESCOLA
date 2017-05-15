/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jeanderson
 */
@Entity
@Table(name = "contatos")
public class Contato implements Serializable {

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
    
    @Id
    @GeneratedValue
    @Column(name = "contato_id")
    public Integer getId() {
        return this.id.get();
    }
    @Column(name = "nome")
    public String getNome() {
        return nome.get();
    }
    @Column(name = "telefone")
    public String getTelefone() {
        return telefone.get();
    }
    @Column(name = "observacao")
    public String getObservacao() {
        return observacao.get();
    }
    
    @Column(name = "data_retorno")
    public LocalDate getData_retorno() {
        return data_retorno;
    }
    @OneToOne()
    @JoinColumn(name = "curso_id")
    public Curso getCurso_pretendido() {
        return curso_pretendido;
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
