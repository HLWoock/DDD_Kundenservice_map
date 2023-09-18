package de.woock.infra.converter.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Anfrage_Converter_ModelMapper implements Converter<Anfrage, AnfrageEntity> {
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public AnfrageEntity convert(Anfrage anfrage) {
		log.debug("converting...");

		AnfrageEntity anfrage_ = mapper.map(anfrage, AnfrageEntity.class);
		
		return anfrage_;
	}

}
