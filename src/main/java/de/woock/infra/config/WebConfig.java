package de.woock.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.woock.infra.converter.AnfrageConverter;
import de.woock.infra.converter.Anfrage_Converter;
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
        registry.addConverter(anfrageConverter());
        registry.addConverter(anfrage_Converter());
    }

    @Bean
    public AnfrageConverter anfrageConverter() {
        return new AnfrageConverter();
    }
    @Bean
    public Anfrage_Converter anfrage_Converter() {
    	return new Anfrage_Converter();
    }
}
