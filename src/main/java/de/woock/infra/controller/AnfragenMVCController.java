package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.domain.Anfrage;
import de.woock.domain.Status;
import de.woock.infra.converter.AnfrageConverter;
import de.woock.infra.dto.AnfrageDTO;
import de.woock.infra.dto.WeiterleitenDTO;
import de.woock.infra.entity.AnfrageEntity;
import de.woock.infra.metrics.MetricsService;
import de.woock.infra.service.AnfragenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RequiredArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private final AnfragenService anfragenService;
	private final MetricsService metricsService;

	@GetMapping({"/", "/index"})
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView("index");
    	
    	long anfragen    = anfragenService.anzahlAnfragen();
    	long beschwerden = anfragenService.anzahlBeschwerden();
    	
    	model.addObject("anfragen", anfragen);
    	model.addObject("beschwerden", beschwerden);
    	
        return model;
    }

	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/anfragen")
	public ModelAndView  alleAnfragen() {
		ModelAndView model = new ModelAndView("anfragen");
		model.addObject("anfragen", anfragen());
		return model;
	}
	
	@GetMapping("/neueAnfrage")
	public ModelAndView neueAnfrageForm() {
		ModelAndView model = new ModelAndView("neueAnfrage");
		model.addObject("anfrage", new AnfrageDTO());
		return model;
	}
	
	@PostMapping("/neueAnfrage")
	public String neueAnfrage(@ModelAttribute("anfrage") AnfrageDTO anfrageDto, BindingResult result, Model model) {
		log.debug("neue Anfrage: '{}' mit Prio {} wird gestellt", anfrageDto.getAnfrage(), anfrageDto.getPrio());
		Anfrage anfrage = null;
//		try {
			anfrage = AnfrageConverter.toAnfrage(anfrageDto);
//		} catch (LeeresFeldFehler e) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(result, e.feld(), "feld.nicht.leer");
//		}
		if (result.hasErrors()) {
			log.error("es {} {} Fehler beim Anlegen einer Anfrage aufgetreten.", result.getErrorCount()==1 ? "ist": "sind", result.getErrorCount());
			return "/neueAnfrage";
		}
		anfragenService.heuteGestellt(anfrage);
		log.debug("neue Anfrage: {}", anfrage);
		return "redirect:/anfragen";
	}
	
	
	@GetMapping("/anfrage/{anfrageId}/bearbeiten")
	public ModelAndView anfrageBearbeitenForm(@PathVariable Long anfrageId) {
		ModelAndView  model         = new ModelAndView("anfrageBearbeiten");
		AnfrageEntity anfrageEntity = anfragenService.anfrageEntity(anfrageId);
		
		model.addObject("statuus", Status.values()); 
		model.addObject("anfrage", AnfrageConverter.toDto(anfrageEntity));
		
		log.debug("Anfrage {}/{} wird zur Bearbeitung in die Anzeige gebracht", anfrageEntity.getId(), 
				                                                                anfrageEntity.getVersion());
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/bearbeiten")
	public String anfrageBearbeiten(@ModelAttribute("anfrage") AnfrageDTO anfrageDTO) {
		log.debug("Anfrage {} fertig bearbeitet", anfrageDTO.getId());
		
		anfragenService.anfrageAktualisieren(anfrageDTO);

		metricsService.increment("stattauto.anfragen");
		
		return "redirect:/anfragen";
	}	
	
	@GetMapping("/anfrage/{anfrageId}/weiterleiten")
	public ModelAndView anfrageWeiterleitenForm(@PathVariable Long anfrageId) {
		ModelAndView model   = new ModelAndView("anfrageWeiterleiten");
		Anfrage      anfrage = anfragenService.anfrage(anfrageId);
		
		model.addObject("anfrage", new WeiterleitenDTO(anfrageId, anfrage.getAnfrage(), false, false, false));
		log.debug("Anfrage {} wird gerade zur Weiterleitung in die Anzeige gebracht", anfrage.getId());
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/weiterleiten")
	public String anfrageWeiterleiten(@ModelAttribute("anfrage") WeiterleitenDTO weiterleitenDTO) {
		log.debug("Anfrage {} weiterleiten", weiterleitenDTO.getId());
		anfragenService.anfrageWeiterleiten(weiterleitenDTO.getId(), AnfrageConverter.konvertiere(weiterleitenDTO));
 
		return "redirect:/anfragen";
	}
	
	@DeleteMapping("/anfrage/{anfrageId}")
	public String anfrageLoeschen(@PathVariable Long anfrageId) {
		log.debug("Anfrage {} loeschen", anfrageId);
		anfragenService.anfrageLoeschen(anfrageId);
		
		return "redirect:/anfragen";
	}
	
	
	private List<AnfrageDTO> anfragen() {
		return  anfragenService.alle()
                               .stream()
                               .map(anfrage -> AnfrageConverter.toDto(anfrage))
                               .collect(Collectors.toList());
	}

}