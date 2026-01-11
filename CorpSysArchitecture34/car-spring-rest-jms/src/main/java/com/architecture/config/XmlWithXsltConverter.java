package com.architecture.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

// XSL трансформация

@Component
public class XmlWithXsltConverter extends MappingJackson2XmlHttpMessageConverter {

    public XmlWithXsltConverter() {
        super(new XmlMapper());
    }

    @Override
    protected void writePrefix(JsonGenerator g, Object object) throws IOException { //добавление тэга в начало
        super.writePrefix(g, object);
        if (g instanceof ToXmlGenerator) {
             ToXmlGenerator xmlGen = (ToXmlGenerator) g;
             object.getClass().getSimpleName();
             String xsl = "style-default.xsl";
             
             if(object.toString().contains("Manufacturer")) xsl = "style-manufacturers.xsl";
             if(object.toString().contains("Car")) xsl = "style-cars.xsl";
             
             
             try {
                
             } catch (Exception e) {}
        }
    }
}
