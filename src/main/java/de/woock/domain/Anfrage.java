package de.woock.domain;

import static de.woock.domain.Status.AUFGENOMMEN;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import de.woock.Kundenservice;
import lombok.Data;

@Data
public class Anfrage {
	
	private Long   id;
	private String anfrage;
	private String antwort;
	private String von;
	private Status status;
	
	public Anfrage stellen(String anfrage) {
		this.anfrage = anfrage;
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.GERMAN);
		this.von     = dateFormat.format(new Date());
		this.status  = AUFGENOMMEN;
		return Kundenservice.anfragenOrdner.abheften(this);	
	}
	
	public String text() {
		return anfrage;
	}

	public Anfrage weiterleitenAn(Abteilungen fuhrpark) {
		Kundenservice.anfragenBoard.neueAnfrageFuerAbteilung(this, fuhrpark);
		return this;
	}

	public void beantworten(String antwort) {
		this.antwort = antwort;
		Kundenservice.anfragenOrdner.updaten(this);
	}

}
