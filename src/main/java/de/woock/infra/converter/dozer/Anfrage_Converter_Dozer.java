package de.woock.infra.converter.dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.core.convert.converter.Converter;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Anfrage_Converter_Dozer implements Converter<Anfrage, AnfrageEntity> {
	
	private DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Override
	public AnfrageEntity convert(Anfrage anfrage) {
		log.debug("converting...");

		AnfrageEntity anfrage_ = mapper.map(anfrage, AnfrageEntity.class);
		
		return anfrage_;
	}

}
