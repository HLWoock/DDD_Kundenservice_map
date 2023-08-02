package de.woock;

import static de.woock.domain.Abteilungen.Fuhrpark;
import static de.woock.domain.Abteilungen.Verein;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.woock.domain.Anfrage;
import de.woock.domain.AnfragenBoard;
import de.woock.domain.AnfragenOrdner;
import de.woock.infra.message.Ausgang;
import de.woock.infra.repository.Anfragen;

@SpringBootApplication
public class Kundenservice {
	
	public static void main(String[] args) {
		SpringApplication.run(Kundenservice.class, args);
	}
	
	@Bean
	public ApplicationRunner test(Anfragen anfragen, Ausgang ausgang) {
		return args -> {
			AnfragenOrdner anfragenOrdner = AnfragenOrdner.mit(anfragen);
			AnfragenBoard  anfragenBoard  = AnfragenBoard.mit(ausgang);
			
			new Anfrage().mit(anfragenOrdner, anfragenBoard)
			             .stellen("Wann kommen endlich die versprochenen Jetski?");
//			             .weiterleitenAn(Fuhrpark)
//			             .beantworten("In 2 Wochen");
			
			new Anfrage().mit(anfragenOrdner, anfragenBoard)
                         .stellen("Was kostet die Mitgliedschaft fuer ein Jahr?");
//			             .weiterleitenAn(Verein)
//			             .beantworten("50 Euro");
		};
	}

}
