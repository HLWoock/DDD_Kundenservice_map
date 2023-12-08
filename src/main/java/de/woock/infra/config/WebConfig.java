package de.woock.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("rawtypes")
    @Bean
    public ConversionService conversionService(List<Converter> converters) {
    	converters.forEach(converter -> log.debug(converter.getClass().getCanonicalName()));
        final DefaultConversionService conversionService = new DefaultConversionService();
        converters.forEach(conversionService::addConverter);
        return conversionService;
    }
    
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(anfrage2Entity_Converter());
//        registry.addConverter(anfrage2Entity_Converter());
//    }
//
//    @Bean
//    public Anfrage2Entity_Converter anfrage2Entity_Converter() {
//        return new Anfrage2Entity_Converter();
//    }
//    @Bean
//    public DTO2AnfrageConverter dTO2AnfrageConverter() {
//    	return new DTO2AnfrageConverter();
//    }
}
