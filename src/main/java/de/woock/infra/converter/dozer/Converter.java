package de.woock.infra.converter.dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.message.Umfrage;
import de.woock.infra.repository.AnfrageDTO;

@Component
public class Converter {
	
	private static DozerBeanMapper mapper = new DozerBeanMapper();

	public static Umfrage toUmfrage(Anfrage anfrage) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(anfrage, Umfrage.class);
	}

	public static AnfrageDTO toDto(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, AnfrageDTO.class);
	}	
	
	public static AnfrageEntity toEntity(Anfrage anfrage) {
		return mapper.map(anfrage, AnfrageEntity.class);
	}
	
	public static Anfrage toDomain(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, Anfrage.class);
	}
}
