package de.woock.infra.converter.dozer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.AnfrageEntity;

@SpringBootTest
class Converter {
	
	AnfrageConverter_Dozer converter = new AnfrageConverter_Dozer();

	@Test
	void testConvert() {
		AnfrageEntity anfrage_ = new AnfrageEntity("Wann und wo?");
		Anfrage  anfrage  = converter.convert(anfrage_);
		assertThat(anfrage.text()).isEqualTo(anfrage_.getAnfrage());
	}

}
