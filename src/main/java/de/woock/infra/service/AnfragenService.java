package de.woock.infra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import de.woock.Kundenservice;
import de.woock.domain.Anfrage;
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
	private       DozerBeanMapper    mapper = new DozerBeanMapper();

	public List<Anfrage>alleAnfragen() {
		log.debug("alleAnfragen");
		return Kundenservice.anfragenOrdner.alleAnfragen();
	}
	

	public Anfrage hinzufuegen(Anfrage anfrage) {
		log.debug("füge Anfrage hinzu: {}", anfrage.text());
		AnfrageEntity anfrageEntity = anfragenRepository.save(toEntity(anfrage));
		return toDomain(anfrageEntity);
	}

	public List<Anfrage> alle() {
		log.debug("alle");
		final List<Anfrage> anfragen = anfragenRepository.findAll()
				                                         .stream()
				                                         .map(anfrage -> toDomain(anfrage))
				                                         .collect(Collectors.toList());
		return anfragen;
	}
	
	private AnfrageEntity toEntity(Anfrage anfrage) {
		return mapper.map(anfrage, AnfrageEntity.class);
	}
	
	private Anfrage toDomain(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, Anfrage.class);
	}	
}
