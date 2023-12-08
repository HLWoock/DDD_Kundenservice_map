package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.domain.Anfrage;
import de.woock.infra.converter.AnfrageConverter;
import de.woock.infra.repository.AnfrageDTO;
import de.woock.infra.service.AnfragenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RequiredArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private final AnfragenService anfragenService;

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
			anfrage = AnfrageConverter.DTO2Anfrage(anfrageDto);
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
	
	
	private List<AnfrageDTO> anfragen() {
		return  anfragenService.alle()
                               .stream()
                               .map(anfrage -> AnfrageConverter.toDto(anfrage))
                               .collect(Collectors.toList());
	}

}