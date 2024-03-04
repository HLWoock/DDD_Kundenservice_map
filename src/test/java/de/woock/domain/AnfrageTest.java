package de.woock.domain;

import static de.woock.domain.Abteilungen.*;
import org.junit.jupiter.api.Test;

public class AnfrageTest {

	@Test
	void contextLoads() {
	}
	
	@Test
	void neueAnfrage() {
		new Anfrage().stellen("Wann kommen endlich die versprochenen Jetski?")
                     .weiterleitenAn(Fuhrpark)
                     .beantworten("In 2 Wochen");

		new Anfrage().stellen("Was kostet die Mitgliedschaft fuer ein Jahr?")
                     .weiterleitenAn(Verein)
                     .beantworten("50 Euro");
	}
}
