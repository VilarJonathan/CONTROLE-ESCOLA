/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            sessao.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    /**
     * Retorna uma Lista de todos os dados de uma, ou mais Tabelas.
     * @param className Informe o nome da Classe que persiste os dados no banco.
     * @return Uma lista com os dados retornados. Obs: pode ser vazia.
     */
    public static ObservableList pegarTodosDados(String className) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transacao = sessao.beginTransaction();
            List lista = sessao.createQuery("FROM " + className.substring(0, 1).toUpperCase().concat(className.substring(1))).list();
            ObservableList lista_pronta = FXCollections.observableList(lista);
            return lista_pronta;            
        } catch (HibernateException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return FXCollections.observableArrayList();
        } finally {
            sessao.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
