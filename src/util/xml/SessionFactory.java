/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe que representa o session-factory do arquivo xml.
 * @author Jeanderson
 */
@XStreamAlias(value = "session-factory")
public class SessionFactory {
    @XStreamImplicit()
    @XStreamAlias(value = "property")
    private List<Property> listaProperty;
    
    @XStreamAlias(value = "mapping")
    @XStreamImplicit
    private List<Mapping> listaMapping;

    public SessionFactory() {
        this.listaProperty = new ArrayList<>();
        this.listaMapping = new ArrayList<>();
    }

    public SessionFactory(List<Property> listaProperty, List<Mapping> listaMapping) {
        this.listaProperty = listaProperty;
        this.listaMapping = listaMapping;
    }

    public List<Property> getListaProperty() {
        return listaProperty;
    }

    public List<Mapping> getListaMapping() {
        return listaMapping;
    }
    
    
    
}
