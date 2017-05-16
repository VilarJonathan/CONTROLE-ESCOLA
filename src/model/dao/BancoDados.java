/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Jeanderson
 */
public class BancoDados {
    /**
     * Método que utilizando o Hibernate faz a persistencia do dado no banco.
     * @param objeto Objeto a ser persistido.
     */
    public static void salvar(Object objeto){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.save(objeto);
        transacao.commit();
        sessao.close();
        HibernateUtil.getSessionFactory().close();
    }
}
