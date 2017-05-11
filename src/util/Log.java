/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por salvar log em forma de arquivo de texto.
 *
 * @author Jeanderson
 */
public class Log {

    /**
     *  Método pega a exceção lançada, e grava em um arquivo de Texto.
     * @param className Nome da Classe de Exceção.
     * @param ex  Exceção lançada.
     */
    public static void salvaLogger(String className, String methodName, Exception ex) {
        try {
            String dataDoOcorrido = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'ás' HH:mm"));
            List<String> msg = new ArrayList<>();
            msg.add("Ocorreu uma exceção na data: " + dataDoOcorrido);
            msg.add("Na Classe: " + className);
            msg.add("Motivo: " + ex.getMessage());
            msg.add("No método: " + methodName);
            msg.add("");

            File arquivo = new File("log.txt");
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            
            Files.write(Paths.get(arquivo.getPath()), msg, StandardOpenOption.APPEND);
        } catch (IOException ex1) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
}
