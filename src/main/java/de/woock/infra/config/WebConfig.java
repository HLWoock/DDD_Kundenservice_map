package de.woock.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.woock.infra.converter.dozer.AnfrageConverter_Dozer;
import de.woock.infra.converter.dozer.Anfrage_Converter_Dozer;
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
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(anfrageConverter_Dozer());
        registry.addConverter(anfrage_Converter_Dozer());
    }

    @Bean
    public AnfrageConverter_Dozer anfrageConverter_Dozer() {
        return new AnfrageConverter_Dozer();
    }
    @Bean
    public Anfrage_Converter_Dozer anfrage_Converter_Dozer() {
    	return new Anfrage_Converter_Dozer();
    }
}
