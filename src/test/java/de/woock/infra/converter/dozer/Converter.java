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
		AnfrageEntity anfrageEntity = new AnfrageEntity("Wann und wo?");
		Anfrage       anfrage       = converter.convert(anfrageEntity);
		
		assertThat(anfrage.text()).isEqualTo(anfrageEntity.getAnfrage());
		assertThat(anfrageEntity.getId()).isEqualTo(anfrageEntity.getId());
	}

}
