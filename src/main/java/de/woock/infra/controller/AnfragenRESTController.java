package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.service.AnfragenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AnfragenRESTController {
	
	private final AnfragenService anfragenService;
	private       DozerBeanMapper mapper = new DozerBeanMapper();
	
	@GetMapping("/anfragen")
	public List<Anfrage> alleAnfragen() {
		log.debug("alle Anfragen");
		
		return anfragenService.alle()
                              .stream()
                              .map(anfrage -> toDomain(anfrage))
                              .collect(Collectors.toList());

	}
	
	private Anfrage toDomain(AnfrageEntity anfrageEntity) {
		return mapper.map(anfrageEntity, Anfrage.class);
	}	

}
