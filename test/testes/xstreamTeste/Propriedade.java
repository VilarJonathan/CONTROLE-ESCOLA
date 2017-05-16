/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.xstreamTeste;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 *
 * @author Jeanderson
 */
@XStreamAlias(value = "property")
@XStreamConverter(value = ToAttributedValueConverter.class,strings = {"valor"})
public class Propriedade {
    @XStreamAlias(value = "name")
    @XStreamAsAttribute
    private String nome;
    
    private String valor;

    public Propriedade(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Propriedade{" + "nome=" + nome + ", valor=" + valor + '}';
    }
    
    
}
