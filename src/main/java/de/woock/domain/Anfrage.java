package de.woock.domain;

import static de.woock.domain.Status.AUFGENOMMEN;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings({ "serial" })
@NoArgsConstructor
@Data
public class Anfrage implements Serializable {
	
	private AnfragenOrdner anfragenOrdner;
	private AnfragenBoard  anfragenBoard;
	
	public Anfrage(AnfragenOrdner anfragenOrdner, AnfragenBoard anfragenBoard) {
		this.anfragenOrdner = anfragenOrdner;
		this.anfragenBoard  = anfragenBoard;
	}
	
	public Anfrage mit(AnfragenOrdner anfragenOrdner, AnfragenBoard anfragenBoard) {
		this.anfragenOrdner = anfragenOrdner;
		this.anfragenBoard  = anfragenBoard;
		return this;
	}

	private Long   id;
	private String anfrage;
	private String antwort;
	private Date   von;
	private Status status;
	
	public Anfrage stellen(String anfrage) {
		this.anfrage = anfrage;
		this.von     = new Date();
		this.status  = AUFGENOMMEN;
		return anfragenOrdner.abheften(this);	
	}

	public Anfrage weiterleitenAn(Abteilungen fuhrpark) {
		anfragenBoard.neueAnfrageFuerAbteilung(this, fuhrpark);
		return this;
	}

	public void beantworten(String antwort) {
		this.antwort = antwort;
		anfragenOrdner.updaten(this);
	}
	
	public List<Anfrage> liste() {
		return anfragenOrdner.alleAnfragen();
	}
}
