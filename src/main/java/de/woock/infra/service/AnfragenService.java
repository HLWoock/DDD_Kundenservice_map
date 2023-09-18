package de.woock.infra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.Kundenservice;
import de.woock.domain.Anfrage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class AnfragenService {
	

	public List<Anfrage>alleAnfragen() {
		log.debug("alleAnfragen");
		return Kundenservice.anfragenOrdner.alleAnfragen();
	}
}
