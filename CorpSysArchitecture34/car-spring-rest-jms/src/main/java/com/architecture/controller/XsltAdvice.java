package com.architecture.controller;

import com.architecture.model.Car;
import com.architecture.model.Manufacturer;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.io.IOException;
import java.util.Collection;

@ControllerAdvice
public class XsltAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2XmlHttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        
        String xslFile = null;
        
        if (body instanceof Collection) {  //вручную перенаправляем на XSLT отображение в зависимости от типа объекта
            Collection<?> c = (Collection<?>) body;
            if (!c.isEmpty()) {
                Object item = c.iterator().next();
                if (item instanceof Manufacturer) xslFile = "style-manufacturers.xsl";
                if (item instanceof Car) xslFile = "style-cars.xsl";
            }
        }
        
        return body;
    }
}
