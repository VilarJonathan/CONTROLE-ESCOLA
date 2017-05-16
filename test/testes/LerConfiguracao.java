/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Jeanderson
 */
public class LerConfiguracao {
    public static void main(String[] args) throws IOException {
        File arquivo = new File("src/hibernate.cfg.xml");
        List<String> linhas_lidas = Files.readAllLines(Paths.get(arquivo.toURI()));
        linhas_lidas.forEach(linha ->{System.out.println(linha);});
    }
}
