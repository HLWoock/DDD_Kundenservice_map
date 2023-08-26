package de.woock.infra.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.Anfrage_;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class Anfragen {
	
	private final AnfragenRepository anfragenRepository;
	private       DozerBeanMapper    mapper = new DozerBeanMapper();

	public Anfrage hinzufuegen(Anfrage anfrage) {
		log.debug("füge Anfrage hinzu: {}", anfrage.getAnfrage());
	    Anfrage_ anfrage_ = mapper.map(anfrage, Anfrage_.class);
		anfrage_ = anfragenRepository.save(anfrage_);
		return mapper.map(anfrage_, Anfrage.class);
	}

	public List<Anfrage> alle() {
		log.debug("alle");
		final List<Anfrage> anfragen = anfragenRepository.findAll()
				                                         .stream()
				                                         .map(anfrage -> mapper.map(anfrage, Anfrage.class))
				                                         .collect(Collectors.toList());
		return anfragen;
	}
	
}
