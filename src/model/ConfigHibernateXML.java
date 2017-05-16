/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import util.Log;
import util.xml.HibernateConfig;

/**
 * Classe que faz as operações de configurações do hibernate e exibi informações referente a mesma.
 * @author Jeanderson
 */
public class ConfigHibernateXML {
    private final XStream xmlStream;
    private HibernateConfig hibernateConfig;
    private final File arquivoConfig;

    public ConfigHibernateXML() {
        this.xmlStream = new XStream();
        this.xmlStream.processAnnotations(HibernateConfig.class);
        this.arquivoConfig = new File("src/hibernate.cfg.xml");
    }
    
    /**
     * Retorna o objeto da Classe HibernateConfig com todas as configurações do XML do hibernate.
     * @return 
     */
    public HibernateConfig getConfigXMl(){       
        this.hibernateConfig = (HibernateConfig) this.xmlStream.fromXML(arquivoConfig);
        return this.hibernateConfig;
    }
    
    /**
     * Método altera ou salva um arquivo de configuração do Hibernate.
     * Obs: o hibernate já vem pre configurado, por favor use o getConfigXMl e edite e depois utilize salvarConfig.
     * @param config 
     */
    public void salvarConfig(HibernateConfig config){
        this.hibernateConfig = config;
        String xmlConfig= this.xmlStream.toXML(this.hibernateConfig);
        List<String> listaParaGravar = new ArrayList<>();
        listaParaGravar.add(xmlConfig);
        try {
            if(this.arquivoConfig.exists()){
                this.arquivoConfig.delete();
            }
            Files.write(Paths.get(this.arquivoConfig.toURI()), listaParaGravar, StandardOpenOption.CREATE_NEW);
        } catch (IOException ex) {
            Log.salvaLogger(ConfigHibernateXML.class.getName(),"salvarConfig", ex);
        }
    }
    
    
}
