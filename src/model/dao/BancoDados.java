/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Log;

/**
 *
 * @author Jeanderson
 */
public class BancoDados {

    /**
     * Método que utilizando o Hibernate faz a persistencia do dado no banco.
     *
     * @param objeto Objeto a ser persistido.
     * @return Informa se a operação ocorreu com sucesso ou não.
     */
    public static boolean salvar(Object objeto) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transacao = sessao.beginTransaction();
            sessao.save(objeto);
            transacao.commit();
            return true;
        } catch (HibernateException ex) {
            Log.salvaLogger(BancoDados.class.getName(), "salvar()", ex);
            return false;
        } finally {
            sessao.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
