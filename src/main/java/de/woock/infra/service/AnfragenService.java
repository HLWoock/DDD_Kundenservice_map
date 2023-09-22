package de.woock.infra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.infra.converter.dozer.Converter;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.repository.Anfragen;
import de.woock.infra.repository.AnfragenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class AnfragenService implements Anfragen {
	
	private final AnfragenRepository anfragenRepository;
	private final Converter          converter;


	public Anfrage hinzufuegen(Anfrage anfrage) {
		log.debug("füge Anfrage hinzu: {}", anfrage.text());
		AnfrageEntity anfrageEntity = anfragenRepository.save(converter.toEntity(anfrage));
		return converter.toDomain(anfrageEntity);
	}

	public List<AnfrageEntity> alle() {
		log.debug("alle");
		return anfragenRepository.findAll();
	}
	
	
}
