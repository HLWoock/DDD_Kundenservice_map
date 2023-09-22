package de.woock.domain;

import de.woock.infra.repository.Anfragen;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AnfragenOrdner {
	
	private static volatile AnfragenOrdner anfragenOrdner;
	private static volatile Anfragen       anfragen;
	
	private AnfragenOrdner(Anfragen anfragen) {
		AnfragenOrdner.anfragen = anfragen;
	}
	
	public static AnfragenOrdner mit(Anfragen anfragen) {
		log.debug("mit");
		if (anfragenOrdner == null) {
            synchronized (AnfragenOrdner.class) {
                if (anfragenOrdner == null) {
                	anfragenOrdner = new AnfragenOrdner(anfragen);
                }
            }
        }
        return anfragenOrdner;
    }	
	
	public Anfrage abheften(Anfrage anfrage) {
		return anfragen.hinzufuegen(anfrage);
	}

	public void updaten(Anfrage anfrage) {
		anfragen.hinzufuegen(anfrage);		
	}

}
