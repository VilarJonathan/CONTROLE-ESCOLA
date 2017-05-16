/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes.xstreamTeste;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jeanderson
 */
public class TesteXstream {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        XStream stream = new XStream();
        stream.processAnnotations(HibernateConfiguracao.class);        

        File arquivo = new File("src/hibernate.cfg.xml");
        InputStream ler = new FileInputStream(arquivo);
        HibernateConfiguracao sessao = (HibernateConfiguracao) stream.fromXML(ler);
        List<Propriedade> lista_de_property = sessao.getSessao().getLista_de_property();
        Propriedade p = lista_de_property.stream().filter(propriedade -> propriedade.getNome().equals("hibernate.connection.url")).findFirst().get();
        System.out.println(p.getNome() + "  " + p.getValor());
    }
    
}
