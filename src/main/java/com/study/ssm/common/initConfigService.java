package com.study.ssm.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class initConfigService extends HttpServlet {
  
    Logger logger=LoggerFactory.getLogger (initConfigService.class);
    
    private static final String DEFAULT_CONFIG_FILE_PATH="config/default.properties";
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public initConfigService() {
       
    }
    
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init ();
        logger.debug ("º”‘ÿdefault.properties≈‰÷√Œƒº˛");
        Properties props=new Properties ();
        if(getInitParameter("loadConfig")!=null){
            File file=new File (initConfigService.class.getResource ("/").getPath ()+this.getServletContext ().getInitParameter ("loadConfig"));
            if(!file.exists ()){
                file=new File (initConfigService.class.getResource ("/").getPath ()+DEFAULT_CONFIG_FILE_PATH); 
            }
            InputStream inStream = null;
            try {
                inStream = new FileInputStream(file);
                props.load (inStream);
                PropertiesConfigurer.processProperties (props);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
}
