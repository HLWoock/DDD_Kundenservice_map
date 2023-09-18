package de.woock.infra.converter.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AnfrageConverter_ModelMapper implements Converter<AnfrageEntity, Anfrage> {
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public Anfrage convert(AnfrageEntity anfrage_) {
		log.debug("converting...");

		Anfrage anfrage = mapper.map(anfrage_, Anfrage.class);
		
		return anfrage;
	}

}
