package de.woock.infra.repository;

import java.io.Serializable;

import de.woock.domain.Prio;
import de.woock.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnfrageDTO implements Serializable{
	
	
	
	public AnfrageDTO(String anfrage) {
		super();
		this.anfrage = anfrage;
	}

	private Long   id;
	private String anfrage;
	private String antwort;
	private String von;
	private Status status;
	private String kunde;
	private Prio   prio;
	
	public Prio[] getPrios() {
		return Prio.values();
	}
}
