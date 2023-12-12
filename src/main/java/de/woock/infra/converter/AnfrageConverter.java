package de.woock.infra.converter;

import static de.woock.domain.Abteilungen.Abrechnung;
import static de.woock.domain.Abteilungen.Fuhrpark;
import static de.woock.domain.Abteilungen.Verein;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import de.woock.domain.Abteilungen;
import de.woock.domain.Anfrage;
import de.woock.infra.dto.AnfrageDTO;
import de.woock.infra.dto.WeiterleitenDTO;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.message.Umfrage;

@Component
public class AnfrageConverter {
	
	private static ModelMapper mapper = new ModelMapper();

	public static Umfrage toUmfrage(Anfrage anfrage) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(anfrage, Umfrage.class);
	}
	
	public static Umfrage toDto(Anfrage anfrage) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(anfrage, Umfrage.class);
	}

	public static AnfrageDTO toDto(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, AnfrageDTO.class);
	}	
	
	public static AnfrageEntity toEntity(Anfrage anfrage) {
		return mapper.map(anfrage, AnfrageEntity.class);
	}
	
	public static Anfrage toAnfrage(AnfrageDTO AnfrageDTO) {
		return mapper.map(AnfrageDTO, Anfrage.class);
	}
	
	public static Anfrage toAnfrage(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, Anfrage.class);
	}

	public static List<Abteilungen> konvertiere(WeiterleitenDTO weiterleitenDto) {
		List<Abteilungen> abteilungen = new ArrayList<>();
		if (weiterleitenDto.getAbrechnung() == true) abteilungen.add(Abrechnung);
		if (weiterleitenDto.getFuhrpark()   == true) abteilungen.add(Fuhrpark);
		if (weiterleitenDto.getVerein()     == true) abteilungen.add(Verein);
		return abteilungen;
	}
}
