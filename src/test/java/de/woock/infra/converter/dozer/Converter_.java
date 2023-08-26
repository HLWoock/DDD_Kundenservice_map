package de.woock.infra.converter.dozer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.domain.Anfrage;
import de.woock.domain.AnfragenBoard;
import de.woock.domain.AnfragenOrdner;
import de.woock.infra.entity.Anfrage_;
import de.woock.infra.message.Ausgang;
import de.woock.infra.repository.Anfragen;

@SpringBootTest
class Converter_ {
	
	@Autowired Anfrage_Converter_Dozer converter;
	@Autowired Anfragen anfragen; 
	@Autowired Ausgang ausgang;

	@Test
	void testConvert() {
		AnfragenOrdner anfragenOrdner = AnfragenOrdner.mit(anfragen);
		AnfragenBoard  anfragenBoard  = AnfragenBoard.mit(ausgang);
		Anfrage anfrage = new Anfrage().mit(anfragenOrdner, anfragenBoard)
                                       .stellen("Wann und wo?");
		Anfrage_ anfrage_ = converter.convert(anfrage);
		assertThat(anfrage_.getAnfrage()).isEqualTo(anfrage.getAnfrage());
	}

}
