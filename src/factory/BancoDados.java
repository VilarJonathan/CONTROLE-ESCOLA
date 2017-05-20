/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

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
import util.StringUtil;

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
    public static boolean save(Object objeto) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.save(objeto);
            transacao.commit();
            return true;
        } catch (HibernateException | NoClassDefFoundError ex) {
            Log.salvaLogger(BancoDados.class.getName(), "save()", ex.getMessage());
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            sessao.close();
        }
    }

    /**
     *  Faz um update no banco. Obs: é necessario que o objeto passado tenho o mesmo ID do objeto que vai ser atualizado no banco.
     * @param objeto
     * @return Informa se a operação ocorreu com sucesso ou não.
     */
    public static boolean update(Object objeto) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction transacao = sessao.beginTransaction();
            sessao.update(objeto);
            transacao.commit();
            return true;
        } catch (HibernateException | NoClassDefFoundError ex) {            
            Log.salvaLogger(BancoDados.class.getName(), "update()", ex.getMessage());
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            sessao.close();
        }

    }

    /**
     * Retorna uma Lista de todos os dados de uma, ou mais Tabelas.
     *
     * @param className Informe o nome da Classe que persiste os dados no banco.
     * @return Uma lista com os dados retornados. Obs: pode ser vazia.
     */
    public static ObservableList queryAll(String className) {
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();
            List lista = sessao.createQuery("FROM " + StringUtil.toUpperCaseFirst(className)).list();
            ObservableList lista_pronta = FXCollections.observableList(lista);
            return lista_pronta;
        } catch (HibernateException | NoClassDefFoundError ex) {
            Log.salvaLogger(BancoDados.class.getName(), "queryAll()", ex.getMessage());
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return FXCollections.observableArrayList();
        } finally {
            sessao.close();
        }
    }
    
    /**
     * Método que prepara o banco de dados.
     * @return Informa se o banco inicializou com sucesso!
     */
    public static boolean inicializar(){
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            sessao.beginTransaction();
            sessao.close();
            return true;
        }catch(HibernateException | NoClassDefFoundError ex){            
            Log.salvaLogger(BancoDados.class.getName(), "inicializar()", ex.getMessage());
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
