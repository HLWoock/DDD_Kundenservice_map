package de.woock.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeiterleitenDTO {

	private Long id;
	
	private String frage;
	
	private Boolean fuhrpark;
	private Boolean abrechnung;
	private Boolean verein;
}
