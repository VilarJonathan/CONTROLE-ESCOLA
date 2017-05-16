/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jeanderson
 */
@Entity
@Table(name = "cursos")
public class Curso implements Serializable {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final IntegerProperty carga_horaria;

    public Curso() {
        this.id = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.carga_horaria = new SimpleIntegerProperty();
    }
    
    @Id
    @GeneratedValue
    @Column(name = "curso_id")
    public int getId(){
        return this.id.get();
    }
    
    @Column(name = "nome", length = 60)
    public String getNome(){
        return this.nome.get();
    }
    @Column(name = "carga_horaria")
    public int getCarga(){
        return this.carga_horaria.get();
    }
    
    protected void setId(int id){
        this.id.set(id);
    }
    
    public void setNome(String nome){
        this.nome.set(nome);
    }
    
    public void setCarga(int carga){
        this.carga_horaria.set(carga);
    }
    
    public IntegerProperty idProperty(){
        return this.id;
    }
    
    public IntegerProperty cargaProperty(){
        return this.carga_horaria;
    }
    
    public StringProperty nomeProperty(){
        return this.nome;
    }
}
