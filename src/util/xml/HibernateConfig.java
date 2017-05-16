/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Classe que representa o Hibernate-configuration do arquivo xml.
 * @author Jeanderson
 */
@XStreamAlias(value = "hibernate-configuration")
public class HibernateConfig {
    @XStreamAlias(value = "session-factory")
    private SessionFactory sessionFactory;

    public HibernateConfig() {
    }

    public HibernateConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
