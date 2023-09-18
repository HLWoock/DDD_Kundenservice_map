package de.woock.infra.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class Anfragen {
	
	private final AnfragenRepository anfragenRepository;
	private       DozerBeanMapper    mapper = new DozerBeanMapper();

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
