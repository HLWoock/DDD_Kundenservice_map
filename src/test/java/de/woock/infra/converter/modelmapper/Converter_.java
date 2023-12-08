package de.woock.infra.converter.modelmapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.domain.Anfrage;
import de.woock.infra.converter.AnfrageConverter;
import de.woock.infra.entity.AnfrageEntity;

@SpringBootTest
class Converter_ {

	@Test
	void testConvert() {
		Anfrage       anfrage       = new Anfrage().stellen("Wann und wo?");
		AnfrageEntity anfrageEntity = AnfrageConverter.toEntity(anfrage);
		
		assertThat(anfrageEntity.getAnfrage()).isEqualTo(anfrage.text());
		assertThat(anfrageEntity.getId()).isNotNull();
	}

}
