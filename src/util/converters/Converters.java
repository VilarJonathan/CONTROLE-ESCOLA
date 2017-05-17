/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Contato;
import model.Curso;
import util.Situacao;

/**
 *
 * @author Jeanderson
 */
public enum Converters implements StringConverter {
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
    },
    LOCAL_DATE {
        @Override
        public javafx.util.StringConverter getConverter() {
            javafx.util.StringConverter<LocalDate> dadosConvertidos = new javafx.util.StringConverter<LocalDate>() {
                @Override
                public String toString(LocalDate data) {
                    if (data != null) {
                        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null) {
                        return LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } else {
                        return null;
                    }
                }
            };
            return dadosConvertidos;
        }

    },
    ENUM_SITUACAO {
        @Override
        public javafx.util.StringConverter getConverter() {
           javafx.util.StringConverter<Situacao> dadosConvertidos = new javafx.util.StringConverter<Situacao>() {
               @Override
               public String toString(Situacao situacao) {
                   return situacao.toString();
               }

               @Override
               public Situacao fromString(String string) {
                   return Situacao.valueOf(string);
               }
           };
           return dadosConvertidos;
        }

    };
}
