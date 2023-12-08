package de.woock;

import static de.woock.domain.Abteilungen.Fuhrpark;
import static de.woock.domain.Abteilungen.Verein;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import de.woock.domain.Anfrage;
import de.woock.domain.AnfragenBoard;
import de.woock.domain.AnfragenOrdner;
import de.woock.infra.service.AnfragenService;

@SpringBootApplication
public class Kundenservice {
	
	public static AnfragenOrdner anfragenOrdner;
	public static AnfragenBoard  anfragenBoard;
	
	public static void main(String[] args) {
		String osName = System.getProperty("os.name").toLowerCase();
		new SpringApplicationBuilder(Kundenservice.class).profiles(osName.contains("windows") ? "windows" : "linux")
		                           		                 .build()
				                                         .run(args);
	}
	
	@Bean
	public ApplicationRunner init(AnfragenService anfragenService, JmsTemplate ausgang) {
		return args -> {
			
			Kundenservice.anfragenOrdner = AnfragenOrdner.mit(anfragenService);
			Kundenservice.anfragenBoard  = AnfragenBoard.mit(anfragenService);
			
			new Anfrage().stellen("Wann kommen endlich die versprochenen Jetski?")
                         .weiterleitenAn(Fuhrpark)
                         .beantworten("In 2 Wochen");
			
			new Anfrage().stellen("Was kostet die Mitgliedschaft fuer ein Jahr?")
			             .weiterleitenAn(Verein)
			             .beantworten("50 Euro");
		};
	}
}
