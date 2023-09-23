package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.infra.converter.dozer.Converter;
import de.woock.infra.repository.AnfrageDTO;
import de.woock.infra.service.AnfragenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AnfragenRESTController {
	
	private final AnfragenService anfragenService;
	private final Converter       converter;
	
	@GetMapping("/anfragen")
	public List<AnfrageDTO> alleAnfragen() {
		log.debug("alle Anfragen");
		
		return anfragenService.alle()
                              .stream()
                              .map(anfrage -> converter.toDto(anfrage))
                              .collect(Collectors.toList());

	}
}
