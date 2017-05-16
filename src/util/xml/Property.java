/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 * Classe que representa o property do arquivo xml.
 * @author Jeanderson
 */
@XStreamAlias(value = "property")
@XStreamConverter(value = ToAttributedValueConverter.class,strings = {"valor"})
public class Property {
    @XStreamAlias(value = "name")
    @XStreamAsAttribute
    private String name;
    private String valor;

    public Property() {
    }

    public Property(String name, String valor) {
        this.name = name;
        this.valor = valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
