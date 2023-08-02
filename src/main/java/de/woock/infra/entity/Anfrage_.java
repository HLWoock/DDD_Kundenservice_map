package de.woock.infra.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings({ "serial" })
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Anfrage_ extends Kopfdaten implements Serializable {

	                             private String anfrage;
	                             private String antwort;
	                             private Date   von;
	@Enumerated(EnumType.STRING) private Status status;
	
	public Anfrage_(String text) {
		this.anfrage = text;
	}

}
