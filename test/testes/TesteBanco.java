/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javafx.collections.ObservableList;
import factory.BancoDados;

/**
 *
 * @author Jeanderson
 */
public class TesteBanco {
    public static void main(String[] args) {
        ObservableList pegarTodosDados = BancoDados.queryAll("curso");
        System.out.println(pegarTodosDados.isEmpty());
    }
    
}
