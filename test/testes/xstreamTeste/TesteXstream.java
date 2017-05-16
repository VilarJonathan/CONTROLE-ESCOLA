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

/**
 *
 * @author Jeanderson
 */
public class TesteXstream {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        XStream stream = new XStream();
        stream.processAnnotations(HibernateConfiguracao.class);
        
//        SessaoFabrica teste = new SessaoFabrica();
//        teste.addProprieda(new Propriedade("org.hibernate", "valor1"));
//        teste.addProprieda(new Propriedade("org.jeanderson", "Valor2"));
//        System.out.println(stream.toXML(teste));
        File arquivo = new File("src/hibernate.cfg.xml");
        InputStream ler = new FileInputStream(arquivo);
        HibernateConfiguracao sessao = (HibernateConfiguracao) stream.fromXML(ler);
        System.out.println(sessao);
//            HibernateConfiguracao config = new HibernateConfiguracao();
//            SessaoFabrica teste = new SessaoFabrica();
//           teste.addProprieda(new Propriedade("org.hibernate", "valor1"));
//           teste.addProprieda(new Propriedade("org.jeanderson", "Valor2"));
//           teste.getMapas().add(new Mapa("classe nome 1"));
//           config.setSessao(teste);
//           System.out.println(stream.toXML(config));
//           List<String> lista = new ArrayList<>();
//           lista.add(stream.toXML(config));
//           File arquivo = new File("teste.xml");
//           if(!arquivo.exists()){
//               arquivo.createNewFile();
//           }
//           Files.write(Paths.get(arquivo.toURI()), lista, StandardOpenOption.WRITE);
            
    }
    
}
