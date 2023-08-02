package de.woock.infra.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.domain.Anfrage;
import de.woock.infra.service.AnfragenService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AnfragenRESTController {
	
	private AnfragenService anfragenService;
	
	@GetMapping("/anfragen")
	public List<Anfrage> alleAnfragen() {
		log.debug("alle Anfragen");
		return anfragenService.alleAnfragen();
	}
}
