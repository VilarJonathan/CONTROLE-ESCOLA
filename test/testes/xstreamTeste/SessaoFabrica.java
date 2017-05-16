/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.xstreamTeste;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeanderson
 */
@XStreamAlias(value = "session-factory")
public class SessaoFabrica {
    @XStreamImplicit()
    @XStreamAlias(value = "property")
    private final List<Propriedade> lista_de_property;
    
    @XStreamAlias(value = "mapping")
    @XStreamImplicit
    private List<Mapa> mapas;

    public SessaoFabrica() {
        this.lista_de_property = new ArrayList<>();
        this.mapas = new ArrayList<>();
    }
    public void addProprieda(Propriedade pro){
        this.lista_de_property.add(pro);
    }

    @Override
    public String toString() {
        return "SessaoFabrica{" + "lista_de_property=" + lista_de_property + '}';
    }

    public List<Mapa> getMapas() {
        return mapas;
    }
    
    
}
