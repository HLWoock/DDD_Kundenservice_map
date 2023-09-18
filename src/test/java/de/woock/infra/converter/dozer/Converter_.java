package de.woock.infra.converter.dozer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;

@SpringBootTest
class Converter_ {
	
	Anfrage_Converter_Dozer converter = new Anfrage_Converter_Dozer();

	@Test
	void testConvert() {
		Anfrage anfrage = new Anfrage().stellen("Wann und wo?");
		AnfrageEntity anfrage_ = converter.convert(anfrage);
		assertThat(anfrage_.getAnfrage()).isEqualTo(anfrage.text());
	}

}
