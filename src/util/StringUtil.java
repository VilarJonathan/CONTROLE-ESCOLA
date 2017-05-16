/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Jeanderson
 */
public class StringUtil {
    /**
     * Retorna a primeira letra maiuscula.
     * @param text Texto que vai ser mudado para letra maiuscula.
     * @return String com a primeira letra maiuscula.
     */
    public static String toUpperCaseFirst(String text){
        return text.substring(0,1).toUpperCase().concat(text.substring(1));
    }
}
