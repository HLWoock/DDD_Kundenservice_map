package de.woock.infra.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@AllArgsConstructor
@Data
public class AnfrageDTO implements Serializable{
	
	private Long   id;
	private String text;

}
