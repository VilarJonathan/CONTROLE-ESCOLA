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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import util.Situacao;

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
    private Situacao situacao;
    
    public Contato() {
        this.id = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.telefone = new SimpleStringProperty();
        this.observacao = new SimpleStringProperty();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "contato_id")
    public Integer getId() {
        return this.id.get();
    }
    @Column(name = "nome", length = 40, nullable = false)
    public String getNome() {
        return nome.get();
    }
    @Column(name = "telefone", length = 20)
    public String getTelefone() {
        return telefone.get();
    }
    @Column(name = "observacao", columnDefinition = "text")
    public String getObservacao() {
        return observacao.get();
    }
    
    @Column(name = "data_retorno")
    public LocalDate getDataRetorno() {
        return data_retorno;
    }
    @OneToOne()
    @JoinColumn(name = "curso_id")
    public Curso getCursoPretendido() {
        return curso_pretendido;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    public Situacao getSituacao() {
        return situacao;
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
    
    public StringProperty nomeProperty() {
        return nome;
    }
    
    public StringProperty telefoneProperty() {
        return telefone;
    }
    
    public StringProperty observacaoProperty() {
        return observacao;
    }
    
    protected void setId(int id) {
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
    
    public void setCursoPretendido(Curso curso){
        this.curso_pretendido = curso;
    }
    
    public void setDataRetorno(LocalDate data){
        this.data_retorno = data;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
    
    
}
