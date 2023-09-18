package de.woock.infra.converter.dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.core.convert.converter.Converter;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AnfrageConverter_Dozer implements Converter<AnfrageEntity, Anfrage> {
	
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Override
	public Anfrage convert(AnfrageEntity anfrage_) {
		log.debug("converting...");

		Anfrage anfrage = mapper.map(anfrage_, Anfrage.class);
		
		return anfrage;
	}

}
