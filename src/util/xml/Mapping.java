/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Classe que representa o mapping do arquivo xml.
 * @author Jeanderson
 */
@XStreamAlias(value = "mapping")
public class Mapping {
    @XStreamAlias(value = "class")
    @XStreamAsAttribute
    private String className;

    public Mapping() {
    }

    public Mapping(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    
}
