package de.woock.infra.repository;

import java.io.Serializable;
import java.util.Date;

import de.woock.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnfrageDTO implements Serializable{
	
	private Long   id;
	private String anfrage;
	private String antwort;
	private Date   von;
	private Status status;

}
