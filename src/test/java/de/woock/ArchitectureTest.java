package de.woock;


import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

import de.woock.domain.AnfragenBoard;
import de.woock.domain.AnfragenOrdner;
import de.woock.infra.service.AnfragenService;

@SpringBootTest
public class ArchitectureTest {

	@Test
	public void dependencyTest() {
		JavaClasses jc = new ClassFileImporter().importPackages("de.woock");
		
		ArchRule  r1 = noClasses().that()
				                  .resideInAPackage("..infra..")
				                  .should().onlyDependOnClassesThat()
				                  .resideInAPackage("..domain..");
		r1.check(jc);
	}
	
	@Test
	public void layerTest() {
		JavaClasses jc = new ClassFileImporter().importPackages("de.woock");
		
		ArchRule rule = layeredArchitecture().consideringAllDependencies()

	            .layer("Infra").definedBy("de.woock.infra..")
	            .layer("Domain").definedBy("de.woock.domain..")
	            .layer("Main").definedBy("de.woock")
	            .whereLayer("Infra").mayNotBeAccessedByAnyLayer()
	            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Infra")
	            .ignoreDependency(Kundenservice.class, AnfragenService.class)
	            .ignoreDependency(Kundenservice.class, AnfragenOrdner.class)
	            .ignoreDependency(Kundenservice.class, AnfragenBoard.class)
	            .ignoreDependency(ArchitectureTest.class, AnfragenService.class)
	            .ignoreDependency(ArchitectureTest.class, AnfragenOrdner.class)
	            .ignoreDependency(ArchitectureTest.class, AnfragenBoard.class);
		
		rule.check(jc);
	}
}
