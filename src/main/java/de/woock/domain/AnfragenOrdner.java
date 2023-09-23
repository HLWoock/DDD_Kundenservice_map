package de.woock.domain;

public class AnfragenOrdner {
	
	private static volatile AnfragenOrdner anfragenOrdner;
	private static volatile Anfragen       anfragen;
	
	private AnfragenOrdner(Anfragen anfragen) {
		AnfragenOrdner.anfragen = anfragen;
	}
	
	public static AnfragenOrdner mit(Anfragen anfragen) {
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
