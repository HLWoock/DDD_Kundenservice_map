package de.woock.infra.converter.modelmapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.domain.Anfrage;
import de.woock.infra.entity.Anfrage_;

@SpringBootTest
class Converter {

	@Autowired AnfrageConverter_ModelMapper converter;

	@Test
	void testConvert() {
		Anfrage_ anfrage_ = new Anfrage_("Wann und wo?");
		Anfrage  anfrage  = converter.convert(anfrage_);
		assertThat(anfrage.getAnfrage()).isEqualTo(anfrage_.getAnfrage());
	}

}
