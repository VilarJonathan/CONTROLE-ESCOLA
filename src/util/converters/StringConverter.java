/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.converters;

/**
 * Interface que vai obrigar as todos enum a implementar o método que retorna uma StringConverter.
 * @author Jeanderson
 */
public interface StringConverter {
    /**
     * Deve retorna uma StringConverter implementada de acordo com a Classe.
     * @return StringConverter.
     */
    public javafx.util.StringConverter getConverter();
}
