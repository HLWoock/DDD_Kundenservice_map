package de.woock.infra.converter.modelmapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;

@SpringBootTest
class Converter_ {

	Anfrage_Converter_ModelMapper converter = new Anfrage_Converter_ModelMapper();

	@Test
	void testConvert() {
		Anfrage anfrage = new Anfrage().stellen("Wann und wo?");
		AnfrageEntity anfrage_ = converter.convert(anfrage);
		assertThat(anfrage_.getAnfrage()).isEqualTo(anfrage.text());
	}

}
