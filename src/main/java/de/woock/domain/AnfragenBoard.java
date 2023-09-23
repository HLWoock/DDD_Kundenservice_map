package de.woock.domain;

public class AnfragenBoard  {
	
	private static volatile AnfragenBoard anfragenBoard;
	private static volatile Ausgang       ausgang;
	
	private AnfragenBoard(Ausgang ausgang) {
		AnfragenBoard.ausgang = ausgang;
	}
	
	public static AnfragenBoard mit(Ausgang ausgang) {
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
		ausgang.neueAnfrageFuerAbteilung(anfrage, abteilung);
		
	}	

}
