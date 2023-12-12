package de.woock.infra.entity;

import static de.woock.domain.Status.BEANTWORTET;
import static de.woock.domain.Status.IN_ARBEIT;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.woock.domain.Status;
import de.woock.infra.dto.AnfrageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SuppressWarnings({ "serial" })
@Log4j2
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class AnfrageEntity extends Kopfdaten implements Serializable {

	                             private String anfrage;
	                             private String antwort;
	                             private String von;
	@Enumerated(EnumType.STRING) private Status status;
	
	public AnfrageEntity(String text) {
		this.anfrage = text;
	}

	public void update(AnfrageDTO anfrageDTO) {
		log.debug("Anfrage {}/{} wird upgedated", getId(), getVersion());
		if (anfrageDTO.getAntwort().isEmpty()) {
			status = IN_ARBEIT;
		} else {
			antwort = anfrageDTO.getAntwort();
			status = BEANTWORTET;
		}
	}
}
