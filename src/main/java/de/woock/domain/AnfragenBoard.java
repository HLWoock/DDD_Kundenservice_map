package de.woock.domain;

import org.dozer.DozerBeanMapper;
import org.springframework.jms.core.JmsTemplate;

import de.woock.infra.message.Umfrage;

public class AnfragenBoard {
	
	private static volatile AnfragenBoard anfragenBoard;
	private static volatile JmsTemplate   ausgang;
	
	private AnfragenBoard(JmsTemplate ausgang) {
		AnfragenBoard.ausgang = ausgang;
	}
	
	public static AnfragenBoard mit(JmsTemplate ausgang) {
		if (anfragenBoard == null) {
            synchronized (AnfragenBoard.class) {
                if (anfragenBoard == null) {
                	anfragenBoard = new AnfragenBoard(ausgang);
                }
            }
        }
        return anfragenBoard;
    }	

	
	public void neueAnfrageFuerAbteilung(Anfrage anfrage, Abteilungen abteilung) {
		ausgang.send(abteilung.name(), 
                     session -> session.createObjectMessage(toUmfrage(anfrage)));
	}
	
	private Umfrage toUmfrage(Anfrage anfrage) {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper.map(anfrage, Umfrage.class);
	}

}
