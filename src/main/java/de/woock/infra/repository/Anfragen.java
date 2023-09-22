package de.woock.infra.repository;

import java.util.List;

import de.woock.domain.Anfrage;

public interface Anfragen {
	
	public Anfrage hinzufuegen(Anfrage anfrage); 
	public List<Anfrage> alle();
}
