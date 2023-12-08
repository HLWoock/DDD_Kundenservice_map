package de.woock.infra.service;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import de.woock.domain.Abteilungen;
import de.woock.domain.Anfrage;
import de.woock.domain.Anfragen;
import de.woock.domain.Ausgang;
import de.woock.infra.converter.AnfrageConverter;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.repository.AnfragenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class AnfragenService implements Anfragen, Ausgang {
	
	private final AnfragenRepository anfragenRepository;
	private final JmsTemplate        jmsTemplate;


	public Anfrage hinzufuegen(Anfrage anfrage) {
		log.debug("füge Anfrage hinzu: {}", anfrage.text());
		AnfrageEntity anfrageEntity = anfragenRepository.save(AnfrageConverter.toEntity(anfrage));
		return AnfrageConverter.toAnfrage(anfrageEntity);
	}

	public List<AnfrageEntity> alle() {
		return anfragenRepository.findAll();
	}
	
	@Override
	public void neueAnfrageFuerAbteilung(Anfrage anfrage, Abteilungen abteilung) {
		jmsTemplate.send(abteilung.name(), 
                         session -> session.createObjectMessage(AnfrageConverter.toUmfrage(anfrage)));
	}

	public long anzahlAnfragen() {
		return anfragenRepository.count();
	}

	public long anzahlBeschwerden() {
		return 0;
	}

	public void heuteGestellt(Anfrage anfrage) {
		anfrage.stellen(anfrage.getAnfrage());
		
	}
	public Anfrage anfrage(Long anfrageId) {
		AnfrageEntity anfrageEntity = anfragenRepository.findById(anfrageId)
				                                        .orElse(new AnfrageEntity());
		return AnfrageConverter.toAnfrage(anfrageEntity);
	}
	
	public void anfrageWeiterleiten(Long anfrageId, List<Abteilungen> abteilungen) {
		AnfrageEntity anfrageEntity = anfragenRepository.findById(anfrageId).orElse(new AnfrageEntity());
		abteilungen.forEach(abteilung -> AnfrageConverter.toAnfrage(anfrageEntity).weiterleitenAn(abteilung));
	}
}
