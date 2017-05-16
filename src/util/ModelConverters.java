/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Contato;
import model.Curso;
import model.interfaces.StringConverter;

/**
 *
 * @author Jeanderson
 */
public enum ModelConverters implements StringConverter {
    CURSO {
        @Override
        public javafx.util.StringConverter getConverter() {
            javafx.util.StringConverter<Curso> dadosConvertidos = new javafx.util.StringConverter<Curso>() {
                @Override
                public String toString(Curso curso) {
                    return curso.getNome();
                }

                @Override
                public Curso fromString(String string) {
                    return null;
                }
            };
            return dadosConvertidos;
        }
    },
    CONTATO {
        @Override
        public javafx.util.StringConverter getConverter() {
            javafx.util.StringConverter<Contato> dadosConvertidos = new javafx.util.StringConverter<Contato>() {
                @Override
                public String toString(Contato contato) {
                    return contato.getNome();
                }
               
                @Override
                public Contato fromString(String string) {
                    return null;
                }
            };
            return dadosConvertidos;
        }
    };
}
