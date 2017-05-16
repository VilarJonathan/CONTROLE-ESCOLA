/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.xstreamTeste;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author Jeanderson
 */
@XStreamAlias(value = "mapping")
public class Mapa {
    @XStreamAlias(value = "class")
    @XStreamAsAttribute
    private String className;

    public Mapa(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Mapa{" + "className=" + className + '}';
    }
    
    
}
