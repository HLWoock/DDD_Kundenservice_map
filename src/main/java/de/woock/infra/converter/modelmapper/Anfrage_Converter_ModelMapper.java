package de.woock.infra.converter.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.domain.AnfragenBoard;
import de.woock.domain.AnfragenOrdner;
import de.woock.infra.entity.Anfrage_;
import de.woock.infra.message.Ausgang;
import de.woock.infra.repository.Anfragen;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class Anfrage_Converter_ModelMapper implements Converter<Anfrage, Anfrage_> {
	
	private ModelMapper mapper = new ModelMapper();
	private Anfragen    anfragen; 
	private Ausgang     ausgang;
	
	@Override
	public Anfrage_ convert(Anfrage anfrage) {
		log.debug("converting...");
		AnfragenOrdner anfragenOrdner = AnfragenOrdner.mit(anfragen);
		AnfragenBoard anfragenBoard   = AnfragenBoard.mit(ausgang);

		Anfrage_ anfrage_ = mapper.map(anfrage, Anfrage_.class);
		
		anfrage.mit(anfragenOrdner,  anfragenBoard);
		
		return anfrage_;
	}

}
