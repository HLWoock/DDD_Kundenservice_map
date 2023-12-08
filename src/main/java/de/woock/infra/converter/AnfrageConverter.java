package de.woock.infra.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.message.Umfrage;
import de.woock.infra.repository.AnfrageDTO;

@Component
public class AnfrageConverter {
	
	private static ModelMapper mapper = new ModelMapper();

	public static Umfrage toUmfrage(Anfrage anfrage) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(anfrage, Umfrage.class);
	}

	public static AnfrageDTO toDto(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, AnfrageDTO.class);
	}	
	
	public static AnfrageEntity toEntity(Anfrage anfrage) {
		return mapper.map(anfrage, AnfrageEntity.class);
	}
	
	public static Anfrage DTO2Anfrage(AnfrageDTO AnfrageDTO) {
		return mapper.map(AnfrageDTO, Anfrage.class);
	}
	
	public static Anfrage Entity2Anfrage(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, Anfrage.class);
	}
}
