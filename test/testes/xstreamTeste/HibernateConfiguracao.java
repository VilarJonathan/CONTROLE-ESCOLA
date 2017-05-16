/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.xstreamTeste;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Jeanderson
 */
@XStreamAlias(value = "hibernate-configuration")
public class HibernateConfiguracao {
   @XStreamAlias(value = "session-factory")
    private SessaoFabrica sessao;

    public SessaoFabrica getSessao() {
        return sessao;
    }

    public void setSessao(SessaoFabrica sessao) {
        this.sessao = sessao;
    }

    @Override
    public String toString() {
        return "HibernateConfiguracao{" + "sessao=" + sessao + '}';
    }
    
    
}
