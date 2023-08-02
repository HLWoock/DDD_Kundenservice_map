package de.woock.infra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.domain.AnfragenOrdner;
import de.woock.infra.repository.Anfragen;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class AnfragenService {
	
	private Anfragen       anfragen;
//	private AnfragenBoard  anfragenBoard;

	public List<Anfrage>alleAnfragen() {
		log.debug("alleAnfragen");
		return AnfragenOrdner.mit(anfragen).alleAnfragen();
	}
}
