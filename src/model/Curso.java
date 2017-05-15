/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jeanderson
 */
public class Curso {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final IntegerProperty carga_horaria;

    public Curso() {
        this.id = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.carga_horaria = new SimpleIntegerProperty();
    }
    
    public int getId(){
        return this.id.get();
    }
    
    public String getNome(){
        return this.nome.get();
    }
    
    public int getCarga(){
        return this.carga_horaria.get();
    }
    
    public void setId(int id){
        this.id.set(id);
    }
    
    public void setNome(String nome){
        this.nome.set(nome);
    }
    
    public void setCarga(int carga){
        this.carga_horaria.set(carga);
    }
    
    public IntegerProperty getIdProperty(){
        return this.id;
    }
    
    public IntegerProperty getCargaProperty(){
        return this.carga_horaria;
    }
    
    public StringProperty getNomeProperty(){
        return this.nome;
    }
}
